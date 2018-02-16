package com.samsung.flickrclient.ui.generic;

import com.samsung.flickrclient.R;

import java.util.List;

/**
 * Created by sidia on 16/02/18.
 */

public class MyPhotoAdapter<T> extends GenericAdapter {

    private List<T> mItems;

    public void setItems(List<T> items) {
        mItems = items;
        notifyItemRangeInserted(0, items.size());
    }

    @Override
    public int getLayoutId() {
        return R.layout.photo_gallery_item;
    }

    @Override
    public T getObjectForPosition(int position) {
        return mItems.get(position);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }
}
