package ru.saveselovskiy.carwash.Cars;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ellinakuznecova on 10.05.15.
 */
public class Car {
    @SerializedName("id")
    int id;

    @SerializedName("car_num")
    String carNum;

    @SerializedName("model")
    String model;

    @SerializedName("type")
    String type;
}
