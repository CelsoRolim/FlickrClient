package com.samsung.flickrclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidia on 15/02/18.
 */
public class Photos {

    @SerializedName("photos")
    private PhotosGallery mPhotos;

    public PhotosGallery getPhotos() {
        return mPhotos;
    }

    public void setPhotos(PhotosGallery photos) {
        this.mPhotos = photos;
    }
}
