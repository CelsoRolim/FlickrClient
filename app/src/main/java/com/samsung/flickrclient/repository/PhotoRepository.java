package com.samsung.flickrclient.repository;

/**
 * Created by sidia on 09/02/18.
 */

public class PhotoRepository {

    private static PhotoRepository sInstance;

    private PhotoRepository() {
    }

    public static PhotoRepository getInstance() {
        if (sInstance == null) {
            synchronized (PhotoRepository.class) {
                if (sInstance == null) {
                    sInstance = new PhotoRepository();
                }
            }
        }
        return sInstance;
    }
}
