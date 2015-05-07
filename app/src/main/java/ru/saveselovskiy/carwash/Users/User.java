package ru.saveselovskiy.carwash.Users;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergey on 07.05.2015.
 */
public class User {
    @SerializedName("name")
    String name;

    @SerializedName("surname")
    String surname;

    @SerializedName("phone")
    String phone;

    @SerializedName("email")
    String email;
}
