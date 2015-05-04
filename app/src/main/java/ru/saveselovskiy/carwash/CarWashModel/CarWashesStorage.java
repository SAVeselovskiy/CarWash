package ru.saveselovskiy.carwash.CarWashModel;

import ru.saveselovskiy.carwash.Carwash.Carwash;

/**
 * Created by Sergey on 04.05.2015.
 */
public class CarWashesStorage {
    static Carwash[] carWashes;
    public static Carwash[] getCarWashes(){
        return carWashes;
    }
    public static void setCarWashes(Carwash[] cw){
        carWashes = cw;
    }
}
