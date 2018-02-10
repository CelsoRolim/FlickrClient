package com.samsung.flickrclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by celso_guido on 10/02/18.
 */

public class PhotoGalleryItem implements GalleryItem {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mCaption;

    @SerializedName("url_s")
    private String mUrl;

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    @Override
    public String toString() {
        return "mCaption: " + mCaption;
    }
}
