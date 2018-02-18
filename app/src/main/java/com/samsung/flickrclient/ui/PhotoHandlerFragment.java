package com.samsung.flickrclient.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.flickrclient.R;
import com.samsung.flickrclient.events.http.GetPhotosResponse;
import com.samsung.flickrclient.handler.ThumbnailDownloader;
import com.samsung.flickrclient.model.PhotoGalleryItem;
import com.samsung.flickrclient.model.Photos;
import com.samsung.flickrclient.ui.generic.MyPhotoAdapter;
import com.samsung.flickrclient.viewmodel.PhotoGalleryViewModel;

import java.util.List;

/**
 * Created by sidia on 16/02/18.
 */

public class PhotoHandlerFragment extends Fragment {

    private static final String TAG = PhotoHandlerFragment.class.getSimpleName();

    private PhotoHandlerAdapter mPhotoAdapter;

    private Handler mResponseHandler;
    private ThumbnailDownloader.ThumbnailDownloadedListener mResponseListener;

    private ThumbnailDownloader<PhotoHandlerHolder> mThumbnailDownloader;

    private RecyclerView mRecyclerView;
    private TextView mLoadingTextView;

    public static PhotoHandlerFragment newInstance() {

        Bundle args = new Bundle();

        PhotoHandlerFragment fragment = new PhotoHandlerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mResponseHandler = new Handler();

        mThumbnailDownloader = new ThumbnailDownloader<>(mResponseHandler);
        mThumbnailDownloader.setDownloadedListener(new ThumbnailDownloader.ThumbnailDownloadedListener<PhotoHandlerHolder>() {
            @Override
            public void onThumbnailDownloaded(PhotoHandlerHolder target, Bitmap bitmap) {
                Log.d(TAG, "onThumbnailDownloaded");
                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                target.bindDrawable(drawable);
            }
        });
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        mLoadingTextView = view.findViewById(R.id.loading_tv);

        mRecyclerView = view.findViewById(R.id.photo_gallery_recycler_view);
        mRecyclerView.setLayoutManager(
                new GridLayoutManager(getActivity().getApplicationContext(), 3));

        mPhotoAdapter = new PhotoHandlerAdapter();

        mRecyclerView.setAdapter(mPhotoAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final PhotoGalleryViewModel viewModel = ViewModelProviders.of(this)
                .get(PhotoGalleryViewModel.class);

        subscribeToModel(viewModel);
    }

    private void subscribeToModel(final PhotoGalleryViewModel viewModel) {
        viewModel.getGalleryItems().observe(this, new Observer<GetPhotosResponse>() {
            @Override
            public void onChanged(@Nullable GetPhotosResponse photosResponse) {
                Log.d(TAG, "onChanged");
                if (photosResponse != null) {

                    mLoadingTextView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);

                    Photos photosWrapper = photosResponse.getData();

                    mPhotoAdapter.setItems(photosWrapper.getPhotosGallery().getItems());
                }
            }
        });
    }

    private class PhotoHandlerAdapter extends RecyclerView.Adapter<PhotoHandlerHolder> {

        private List<PhotoGalleryItem> mItems;

        @Override
        public PhotoHandlerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Log.d(TAG, "onCreateViewHolder");

            Context context = parent.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(context);

            View view = layoutInflater.inflate(R.layout.photo_gallery_item, parent, false);
            return new PhotoHandlerHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHandlerHolder holder, int position) {

            Log.d(TAG, "onBindViewHolder");

            PhotoGalleryItem currentItem = mItems.get(position);

            Log.d(TAG, "currentItem.getUrl(): " + currentItem.getUrl());

            mThumbnailDownloader.queueThumbnail(holder, currentItem.getUrl());
        }

        @Override
        public int getItemCount() {
            return (mItems == null ? 0 : mItems.size());
        }

        public void setItems(List<PhotoGalleryItem> items) {
            mItems = items;
            notifyItemRangeInserted(0, items.size());
        }
    }

    private class PhotoHandlerHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public PhotoHandlerHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_photo_item);
        }

        public void bindDrawable(Drawable drawable) {
            mImageView.setImageDrawable(drawable);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailDownloader.clearQueue();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
    }
}
