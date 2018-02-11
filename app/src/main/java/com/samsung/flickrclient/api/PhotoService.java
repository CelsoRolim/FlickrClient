package com.samsung.flickrclient.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.samsung.flickrclient.model.PhotoGalleryItem;
import com.samsung.flickrclient.PhotosWrapper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by celso_guido on 10/02/18.
 */

public class PhotoService {

    private static String TAG = PhotoService.class.getSimpleName();

    private static PhotoService sInstance;

    private PhotoServiceApi mPhotoServiceApi;

    private PhotoService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PhotoServiceApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mPhotoServiceApi = retrofit.create(PhotoServiceApi.class);
    }

    public static PhotoService getInstance() {
        if (sInstance == null) {
            synchronized (PhotoService.class) {
                if (sInstance == null) {
                    sInstance = new PhotoService();
                }
            }
        }
        return sInstance;
    }

    public LiveData<PhotosWrapper<PhotoGalleryItem>> getRecent() {

        Map<String, String> mapOptions = new HashMap<>();
        mapOptions.put("api_key", PhotoServiceApi.API_KEY);
        mapOptions.put("format", PhotoServiceApi.JSON_FORMAT);
        mapOptions.put("nojsoncallback", PhotoServiceApi.EXCLUDE_ENCLOSING);
        mapOptions.put("extras", PhotoServiceApi.URL_SMALL_VERSION);

        final MutableLiveData<PhotosWrapper<PhotoGalleryItem>> liveData = new MutableLiveData<>();

        mPhotoServiceApi.getRecent(mapOptions).enqueue(new Callback<PhotosWrapper<PhotoGalleryItem>>() {
            @Override
            public void onResponse(Call<PhotosWrapper<PhotoGalleryItem>> call,
                                   Response<PhotosWrapper<PhotoGalleryItem>> response) {
                Log.d(TAG, "Response: " + response.body());
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<PhotosWrapper<PhotoGalleryItem>> call, Throwable t) {
                Log.d(TAG, "Response: " + t.getMessage());
            }
        });

        return liveData;
    }
}
