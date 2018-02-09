package com.samsung.flickrclient.ui;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by sidia on 09/02/18.
 */

public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
