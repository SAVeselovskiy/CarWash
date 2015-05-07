package ru.saveselovskiy.carwash.CarwashAdapter;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import ru.saveselovskiy.carwash.Login.AuthData;

/**
 * Created by Sergey on 04.05.2015.
 */
public interface CarwashesWorker {
    @GET("/carwashes")
    public void getWashes(Callback<CarWashes> callback);
    @GET("/login")
    public void signIn(@Query("login") String login, @Query("password") String password,Callback<AuthData> callback);
}
