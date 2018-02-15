package com.samsung.flickrclient.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.flickrclient.R;
import com.samsung.flickrclient.databinding.FragmentPhotoGalleryBinding;
import com.samsung.flickrclient.events.http.GetPhotosResponse;
import com.samsung.flickrclient.model.Photos;
import com.samsung.flickrclient.viewmodel.PhotoGalleryViewModel;

/**
 * Created by sidia on 09/02/18.
 */
public class PhotoGalleryFragment extends Fragment {

    private static String TAG = PhotoGalleryFragment.class.getSimpleName();

    private FragmentPhotoGalleryBinding mBinding;

    private PhotoAdapter mPhotoAdapter;

    public static PhotoGalleryFragment newInstance() {

        Bundle args = new Bundle();

        PhotoGalleryFragment fragment = new PhotoGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_photo_gallery, container, false);

        mBinding.photoGalleryRecyclerView.setLayoutManager(
                new GridLayoutManager(getActivity().getApplicationContext(), 3));

        mPhotoAdapter = new PhotoAdapter();
        mBinding.photoGalleryRecyclerView.setAdapter(mPhotoAdapter);

        return mBinding.getRoot();
    }

    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link #setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.  This is called after {@link #onCreateView}
     * and before {@link #onViewStateRestored(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
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

                    Photos photosWrapper = photosResponse.getData();

                    mPhotoAdapter.setItems(photosWrapper.getPhotosGallery().getItems());
                }
            }
        });
    }
}
