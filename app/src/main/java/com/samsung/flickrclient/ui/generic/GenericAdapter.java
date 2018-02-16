package com.samsung.flickrclient.ui.generic;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by sidia on 16/02/18.
 */

public abstract class GenericAdapter extends RecyclerView.Adapter<MyPhotoViewHolder> {

    @Override
    public MyPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        ViewDataBinding binding = DataBindingUtil
                .inflate(layoutInflater, getLayoutId(), parent, false);
        MyPhotoViewHolder holder = new MyPhotoViewHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyPhotoViewHolder holder, int position) {
        holder.bindObject(getObjectForPosition(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 0;
    }

    public abstract int getLayoutId();

    public abstract Object getObjectForPosition(int position);
}
