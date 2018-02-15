package com.samsung.flickrclient.repository;

import android.arch.lifecycle.LiveData;

import com.samsung.flickrclient.api.PhotoService;
import com.samsung.flickrclient.events.http.GetPhotosResponse;
import com.samsung.flickrclient.model.PhotoGalleryItem;

/**
 * Created by sidia on 09/02/18.
 */

public class PhotoRepository {

    private static PhotoRepository sInstance;

    private PhotoService mPhotoService;

    private PhotoRepository(PhotoService photoService) {
        mPhotoService = photoService;
    }

    public static PhotoRepository getInstance(PhotoService photoService) {
        if (sInstance == null) {
            synchronized (PhotoRepository.class) {
                if (sInstance == null) {
                    sInstance = new PhotoRepository(photoService);
                }
            }
        }
        return sInstance;
    }

    public LiveData<GetPhotosResponse> getRecent() {
        return mPhotoService.getRecent();
    }
}
