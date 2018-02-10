package com.samsung.flickrclient.api;

import com.samsung.flickrclient.ListWrapper;
import com.samsung.flickrclient.model.PhotoGalleryItem;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by sidia on 09/02/18.
 */

public interface PhotoServiceApi {

    String BASE_URL = "https://api.flickr.com/services/rest/";

    String API_KEY = "14df8e7e685728e5fe2212e222ae4fad";

    String RECENT_REST_END_POINT = "flickr.photos.getRecent";

    String URL_SMALL_VERSION = "url_s";

    String JSON_FORMAT = "json";

    String EXCLUDE_ENCLOSING = "1";

            @GET
    @Headers({"content-type:application/json", "accept:application/json"})
    Call<ListWrapper<PhotoGalleryItem>> getRecent(@Query("method") String method,
                                                  @Query("api_key") String apiKey,
                                                  @Query("format") String format,
                                                  @Query("nojsoncallback") String callback,
                                                  @Query("extras") String extras);

    @GET
    @Headers({"content-type:application/json", "accept:application/json"})
    Call<ListWrapper<PhotoGalleryItem>> getRecent(@QueryMap Map<String, String> mapOptions);
}
