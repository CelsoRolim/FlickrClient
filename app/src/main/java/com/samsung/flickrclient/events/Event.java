package com.samsung.flickrclient.events;

/**
 * Created by sidia on 15/02/18.
 */

public abstract class Event<T> {

    private T mData;
    private Throwable mError;

    public Event(T data, Throwable error) {
        this.mData = data;
        this.mError = error;
    }

    public Event() {
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        this.mData = data;
    }

    public Throwable getError() {
        return mError;
    }

    public void setError(Throwable error) {
        this.mError = error;
    }

    @Override
    public String toString() {
        return "Event{" +
                " mData= " + mData +
                ", mError= " + mError +
                "}";
    }
}
