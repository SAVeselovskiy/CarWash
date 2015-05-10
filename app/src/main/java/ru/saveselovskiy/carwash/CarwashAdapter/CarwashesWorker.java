package ru.saveselovskiy.carwash.CarwashAdapter;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import ru.saveselovskiy.carwash.Carwash.Carwash;
import ru.saveselovskiy.carwash.Carwash.Timetable;
import ru.saveselovskiy.carwash.Login.AuthData;
import ru.saveselovskiy.carwash.Users.User;

/**
 * Created by Sergey on 04.05.2015.
 */
public interface CarwashesWorker {
    @GET("/carwashes")
    public void getWashes(Callback<CarWashes> callback);

    @GET("/carwashes/{id}")
    public void loadWashWithId(@Path("id") int id, Callback<Carwash> callback);

    @GET("/login")
    public void signIn(@Query("login") String login, @Query("password") String password,Callback<AuthData> callback);

    @GET("/users/{id}")
    public void  loadUserWithId(@Path("id") int id, Callback<User> callback);

    @GET("/carwashes/{id}/timetable")
    public void loadTimetable(@Path("id") int id, Callback<Timetable> callback);
}
