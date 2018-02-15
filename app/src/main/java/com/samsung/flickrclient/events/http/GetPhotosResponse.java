package com.samsung.flickrclient.events.http;

import com.samsung.flickrclient.events.Event;
import com.samsung.flickrclient.model.Photos;

/**
 * Created by sidia on 15/02/18.
 */

public class GetPhotosResponse extends Event<Photos> {
    public GetPhotosResponse(Photos data, Throwable error) {
        super(data, error);
    }

    @Override
    public String toString() {
        return "GetPhotosResponse{}" + super.toString();
    }
}
