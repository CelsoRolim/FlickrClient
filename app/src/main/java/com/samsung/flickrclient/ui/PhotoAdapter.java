package com.samsung.flickrclient.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.samsung.flickrclient.R;
import com.samsung.flickrclient.databinding.PhotoGalleryItemBinding;
import com.samsung.flickrclient.model.PhotoGalleryItem;

import java.util.List;

/**
 * Created by sidia on 15/02/18.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<PhotoGalleryItem> mItems;

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        int layoutItem = R.layout.photo_gallery_item;

        PhotoGalleryItemBinding binding = DataBindingUtil
                .inflate(inflater, layoutItem, parent, false);

        PhotoViewHolder viewHolder = new PhotoViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        PhotoGalleryItem currentItem = mItems.get(position);
        holder.binding.setPhotoItem(currentItem);

        holder.binding.executePendingBindings();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return (mItems == null ? 0 : mItems.size());
    }

    public void setItems(List<PhotoGalleryItem> items) {
        mItems = items;
        notifyItemRangeInserted(0, items.size());
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {

        PhotoGalleryItemBinding binding;

        public PhotoViewHolder(PhotoGalleryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
