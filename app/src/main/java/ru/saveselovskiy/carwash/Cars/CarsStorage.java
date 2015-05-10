package ru.saveselovskiy.carwash.Cars;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ellinakuznecova on 10.05.15.
 */
public class CarsStorage {
    public static Car[] cars;

    public static Car[] getCars() {
        return cars;
    }

    public static void setCars(Car[] c) { cars = c;}
}
