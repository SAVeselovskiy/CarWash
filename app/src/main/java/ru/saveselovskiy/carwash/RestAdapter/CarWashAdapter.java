package ru.saveselovskiy.carwash.RestAdapter;

import retrofit.RestAdapter;

/**
 * Created by Sergey on 04.05.2015.
 */
public class CarWashAdapter {

    private static String API_URL = "http://54.148.200.53:60180";//
    private static RestAdapter adapter;

    public static RestAdapter getAdapter() {
        if (adapter == null)
            adapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .build();
        return adapter;
    }

    public static String getURL() {
        return API_URL;
    }
}
