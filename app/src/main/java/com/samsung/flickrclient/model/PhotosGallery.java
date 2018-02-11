package com.samsung.flickrclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by celso_guido on 10/02/18.
 */

public class PhotosGallery<T> {

    @SerializedName("page")
    private int mPage;

    @SerializedName("pages")
    private int mPages;

    @SerializedName("photo")
    private List<T> mItems;

    public int getPage() {
        return mPage;
    }

    public void setPage(int mPage) {
        this.mPage = mPage;
    }

    public List<T> getItems() {
        return mItems;
    }

    public void setItems(List<T> mItems) {
        this.mItems = mItems;
    }

    public int getPages() {
        return mPages;
    }

    public void setPages(int mPages) {
        this.mPages = mPages;
    }
}
