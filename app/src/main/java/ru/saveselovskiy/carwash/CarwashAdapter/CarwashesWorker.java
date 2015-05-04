package ru.saveselovskiy.carwash.CarwashAdapter;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Sergey on 04.05.2015.
 */
public interface CarwashesWorker {
    @GET("/carwashes")
    public void getWashes(Callback<CarWashes> callback);
}
