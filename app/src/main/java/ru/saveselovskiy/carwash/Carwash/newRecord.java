package ru.saveselovskiy.carwash.Carwash;

/**
 * Created by Sergey on 14.05.2015.
 */
public class newRecord {
    private String carnum;
    private String date;
    private int washingType;
    private int customerType;

    public newRecord(){};
    public newRecord(String carnum, String date, int washingType, int customerType) {
        this.carnum = carnum;
        this.date = date;
        this.washingType = washingType;
        this.customerType = customerType;
    }
}
