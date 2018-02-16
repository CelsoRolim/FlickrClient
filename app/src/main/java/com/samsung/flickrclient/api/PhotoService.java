package com.samsung.flickrclient.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.samsung.flickrclient.events.http.GetPhotosResponse;
import com.samsung.flickrclient.model.Photos;

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
                //.callbackExecutor()
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

    public LiveData<GetPhotosResponse> getRecent() {

        Map<String, String> mapOptions = new HashMap<>();
        mapOptions.put("api_key", PhotoServiceApi.API_KEY);
        mapOptions.put("format", PhotoServiceApi.JSON_FORMAT);
        mapOptions.put("nojsoncallback", PhotoServiceApi.EXCLUDE_ENCLOSING);
        mapOptions.put("extras", PhotoServiceApi.URL_SMALL_VERSION);

        final MutableLiveData<GetPhotosResponse> liveData = new MutableLiveData<>();

        mPhotoServiceApi.getRecent(mapOptions).enqueue(new Callback<Photos>() {

            // TODO Check if callback is called on main thread

            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                Log.d(TAG, "Response: " + response.body());

                Photos photosResponse = response.body();

                // TODO If it is on the main thread, setValue is the correct method.
                /*
                for (int i = 0; i < 1000000000; i++);
                for (int i = 0; i < 1000000000; i++);
                for (int i = 0; i < 1000000000; i++);
                for (int i = 0; i < 1000000000; i++);
                for (int i = 0; i < 1000000000; i++);
                for (int i = 0; i < 1000000000; i++);
                for (int i = 0; i < 1000000000; i++);
                for (int i = 0; i < 1000000000; i++);
                for (int i = 0; i < 1000000000; i++);*/
                liveData.setValue(new GetPhotosResponse(photosResponse, null));
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                Log.d(TAG, "Response: " + t.getMessage());

                // TODO If it is on the main thread, setValue is the correct method.
                liveData.postValue(new GetPhotosResponse(null, t));
            }
        });

        return liveData;
    }
}
