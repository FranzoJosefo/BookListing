package com.franciscoolivero.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    /** Tag for log messages */
    private static final String LOG_TAG = BookLoader.class.getName();
    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link BookLoader}.
     *
     * @param context of the activity
     * @param mUrl to load data from
     */
    public BookLoader(@NonNull Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "onStartLoading, forceLoad to execute loadInBackground");
        forceLoad();
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground() executed");

        if(mUrl==null){
            return null;
        } else {
            Log.v(LOG_TAG, "mUrl wasn't null, fetchEartquakeData(mUrl) is triggered)");

            List<Book> books = QueryUtils.fetchBookData(mUrl);
            Log.v(LOG_TAG, "Books were correctly fetched, return the list so that onLoadFinished adds them to the adapter");
            return books;

        }
    }
}
