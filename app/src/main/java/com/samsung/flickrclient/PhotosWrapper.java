package com.samsung.flickrclient;

import com.google.gson.annotations.SerializedName;
import com.samsung.flickrclient.model.PhotosGallery;

/**
 * Created by celso_guido on 10/02/18.
 */

public class PhotosWrapper<T> {

    @SerializedName("photos")
    private PhotosGallery<T> mPhotos;

    public PhotosGallery getPhotos() {
        return mPhotos;
    }

    public void setmPhotos(PhotosGallery<T> mPhotos) {
        this.mPhotos = mPhotos;
    }
}
