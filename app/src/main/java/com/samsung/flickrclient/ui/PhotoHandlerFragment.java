package com.samsung.flickrclient.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.samsung.flickrclient.handler.ThumbnailDownloader;

/**
 * Created by sidia on 16/02/18.
 */

public class PhotoHandlerFragment extends Fragment {

    private Handler mResponseHandler;
    private ThumbnailDownloader.ThumbnailDownloadedListener mResponseListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mResponseHandler = new Handler(;
        mResponseListener = new ThumbnailDownloader.ThumbnailDownloadedListener() {
            @Override
            public void onThumbnailDownloaded(T target, Bitmap bitmap) {

            }
        };
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
