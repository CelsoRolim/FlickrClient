package com.samsung.flickrclient.ui.generic;

import android.databinding.ViewDataBinding;

import com.samsung.flickrclient.BR;

/**
 * Created by sidia on 16/02/18.
 */

public class MyPhotoViewHolder extends GenericViewHolder {
    public MyPhotoViewHolder(ViewDataBinding binding) {
        super(binding);
    }

    @Override
    public int getVariableId() {
        return BR.photoItem;
    }
}
