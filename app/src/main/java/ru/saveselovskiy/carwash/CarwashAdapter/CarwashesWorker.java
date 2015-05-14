package ru.saveselovskiy.carwash.CarwashAdapter;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import ru.saveselovskiy.carwash.Cars.Cars;
import ru.saveselovskiy.carwash.Carwash.Carwash;
import ru.saveselovskiy.carwash.Carwash.Timetable;
import ru.saveselovskiy.carwash.Carwash.newRecord;
import ru.saveselovskiy.carwash.Login.AuthData;

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
    public void  loadUserWithId(@Path("id") int id, Callback callback);

    @GET("/users/{id}/cars")
    public void loadUsersCars(@Path("id") int id, Callback<Cars> callback);

    @GET("/carwashes/{id}/timetable")
    public void loadTimetable(@Path("id") int id, Callback<Timetable> callback);

    @POST("/carwashes/{id}/timetable")
    public void postRecord(@Path("id") int id,@Path("date") String date, @Path("carnum") String carnum,
                           @Path("washingType") int washingType, @Path("customerType") int type, Callback callback);//@Body newRecord record);
}
