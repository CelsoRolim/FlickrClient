package com.samsung.flickrclient.api;

import com.samsung.flickrclient.ListWrapper;
import com.samsung.flickrclient.model.PhotoGalleryItem;

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

public class PhotoServiceWrapper {

    private static String TAG = PhotoServiceWrapper.class.getSimpleName();

    private static PhotoServiceWrapper sInstance;

    private PhotoServiceApi mPhotoServiceApi;

    private PhotoServiceWrapper() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PhotoServiceApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mPhotoServiceApi = retrofit.create(PhotoServiceApi.class);
    }

    public static PhotoServiceWrapper getInstance() {
        if (sInstance == null) {
            synchronized (PhotoServiceWrapper.class) {
                if (sInstance == null) {
                    sInstance = new PhotoServiceWrapper();
                }
            }
        }
        return sInstance;
    }

    public void getRecent() {

        Map<String, String> mapOptions = new HashMap<>();
        mapOptions.put("method", PhotoServiceApi.RECENT_REST_END_POINT);
        mapOptions.put("api_key", PhotoServiceApi.API_KEY);
        mapOptions.put("format", PhotoServiceApi.JSON_FORMAT);
        mapOptions.put("nojsoncallback", PhotoServiceApi.EXCLUDE_ENCLOSING);
        mapOptions.put("extras", PhotoServiceApi.URL_SMALL_VERSION);

        mPhotoServiceApi.getRecent(mapOptions).enqueue(new Callback<ListWrapper<PhotoGalleryItem>>() {
            @Override
            public void onResponse(Call<ListWrapper<PhotoGalleryItem>> call, Response<ListWrapper<PhotoGalleryItem>> response) {

            }

            @Override
            public void onFailure(Call<ListWrapper<PhotoGalleryItem>> call, Throwable t) {

            }
        });
    }
}
