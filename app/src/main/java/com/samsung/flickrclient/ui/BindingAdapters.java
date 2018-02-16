package com.samsung.flickrclient.ui;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.samsung.flickrclient.R;

/**
 * Created by sidia on 09/02/18.
 */

public class BindingAdapters {

    private static final String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("setImageUrl")
    public static void setBitmapToImageView(ImageView imageView, String url) {
        Log.d(TAG,"Setting setImageUrl: " + url);

        Context context = imageView.getContext();

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }
}
