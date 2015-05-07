package ru.saveselovskiy.carwash.Login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergey on 07.05.2015.
 */
public class AuthData {
    @SerializedName("id")
    public int id;

    @SerializedName("token")
    public String token;
}
