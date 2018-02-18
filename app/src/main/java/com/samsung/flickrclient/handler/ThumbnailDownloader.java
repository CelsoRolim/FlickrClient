package com.samsung.flickrclient.handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.samsung.flickrclient.api.flickr.FlickrFetchr;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sidia on 16/02/18.
 */

public class ThumbnailDownloader<T> extends HandlerThread {

    private static String TAG = ThumbnailDownloader.class.getSimpleName();

    private static final int MESSAGE_DOWNLOAD = 0;
    private Handler mRequestHandler;

    private Handler mResponseHandler;

    private ConcurrentHashMap<T, String> mRequestMap = new ConcurrentHashMap<>();

    private ThumbnailDownloadedListener<T> mDownloadedListener;

    public ThumbnailDownloader(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_DOWNLOAD:
                        T target = (T) msg.obj;
                        handleRequest(target);
                        break;
                    default:
                        Log.d(TAG, "Unknown message received: " + msg.what);
                }
            }
        };
    }

    public void queueThumbnail(T target, String url) {
        if (url == null) {
            mRequestMap.remove(target);
        } else {
            mRequestMap.put(target, url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD, target)
                    .sendToTarget();
        }
    }

    public void handleRequest(final T target) {

        try {

            final String url = mRequestMap.get(target);

            if (url == null) {
                return;
            }

            byte[] bytes = FlickrFetchr.getUrlBytes(url);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mRequestMap.get(target) != url) {
                        return;
                    }

                    mRequestMap.remove(target);
                    mDownloadedListener.onThumbnailDownloaded(target, bitmap);
                }
            });

        } catch (IOException e) {
            Log.e(TAG, "Error downloading image", e);
        }
    }

    public interface ThumbnailDownloadedListener<T> {
        void onThumbnailDownloaded(T target, Bitmap bitmap);
    }

    public void setDownloadedListener(ThumbnailDownloadedListener<T> listener) {
        mDownloadedListener = listener;
    }

    public void clearQueue() {
        mRequestHandler.removeMessages(MESSAGE_DOWNLOAD);
    }
}
