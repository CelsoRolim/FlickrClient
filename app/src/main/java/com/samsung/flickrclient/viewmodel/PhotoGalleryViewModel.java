package com.samsung.flickrclient.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.samsung.flickrclient.BasicApp;
import com.samsung.flickrclient.PhotosWrapper;
import com.samsung.flickrclient.model.PhotoGalleryItem;
import com.samsung.flickrclient.repository.PhotoRepository;

/**
 * Created by celso_guido on 10/02/18.
 */

public class PhotoGalleryViewModel extends AndroidViewModel {

    private PhotoRepository mRepository;

    private LiveData<PhotosWrapper<PhotoGalleryItem>> mGalleryItems;

    public PhotoGalleryViewModel(@NonNull Application application) {
        super(application);

        mRepository = ((BasicApp) application).getPhotoRepository();

        mGalleryItems = mRepository.getRecent();
    }

    public LiveData<PhotosWrapper<PhotoGalleryItem>> getGalleryItems() {
        return mGalleryItems;
    }
}
