package com.samsung.flickrclient;

import android.app.Application;

import com.samsung.flickrclient.api.PhotoService;
import com.samsung.flickrclient.repository.PhotoRepository;

/**
 * Created by celso_guido on 10/02/18.
 */

public class BasicApp extends Application{

    private PhotoService getPhotoService() {
        return PhotoService.getInstance();
    }

    public PhotoRepository getPhotoRepository() {
        return PhotoRepository.getInstance(getPhotoService());
    }
}
