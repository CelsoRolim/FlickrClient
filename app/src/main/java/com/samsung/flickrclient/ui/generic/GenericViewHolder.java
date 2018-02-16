package com.samsung.flickrclient.ui.generic;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by sidia on 16/02/18.
 */

public abstract class GenericViewHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding mBinding;

    public GenericViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bindObject(Object object) {
        mBinding.setVariable(getVariableId(), object);
        mBinding.executePendingBindings();
    }

    public abstract int getVariableId();
}
