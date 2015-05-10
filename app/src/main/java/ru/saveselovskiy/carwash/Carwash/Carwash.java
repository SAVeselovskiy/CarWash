package ru.saveselovskiy.carwash.Carwash;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergey on 04.05.2015.
 */

public class Carwash {
    @SerializedName("name")
    public String name;

    @SerializedName("phone")
    public String phone;

    @SerializedName("X")
    public double latitude;
    @SerializedName("Y")
    public double longitude;

    @SerializedName("work_time_from")
    public String from;

    @SerializedName("work_time_till")
    public String to;

    @SerializedName("id")
    public int id;
}
