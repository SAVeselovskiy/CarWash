package ru.saveselovskiy.carwash.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.saveselovskiy.carwash.RestAdapter.CarWashAdapter;
import ru.saveselovskiy.carwash.RestAdapter.CarwashesWorker;
import ru.saveselovskiy.carwash.MainActivity;
import ru.saveselovskiy.carwash.R;

/**
 * Created by Sergey on 05.05.2015.
 */
public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hasAccount()){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.login_activity);
        Button signIn = (Button)findViewById(R.id.sign_in);
        final EditText login = (EditText)findViewById(R.id.text_login);
        final EditText password = (EditText)findViewById(R.id.text_password);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Неверные данные!")
                            .setMessage("Вы должны ввести логин!")
                            .setCancelable(false)
                            .setNegativeButton("ОК",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if(password.getText().length()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Неверные данные!")
                            .setMessage("Вы должны ввести пароль!")
                            .setCancelable(false)
                            .setNegativeButton("ОК",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{
                    RestAdapter carWashAdapter = CarWashAdapter.getAdapter();
                    CarwashesWorker carwashesWorker = carWashAdapter.create(CarwashesWorker.class);
                    carwashesWorker.signIn(login.getText().toString(),password.getText().toString(),new Callback<AuthData>() {
                        @Override
                        public void success(AuthData authData, Response response) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            SharedPreferences account = getSharedPreferences("CurrentUser", 0);
                            SharedPreferences.Editor editor = account.edit();
                            editor.putInt("id", authData.id);
                            editor.putString("token",authData.token);
                            editor.commit();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setTitle("Авторизация не удалась")
                                    .setMessage(error.getMessage())
                                    .setCancelable(false)
                                    .setNegativeButton("ОК",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean hasAccount(){
        SharedPreferences account = getSharedPreferences("CurrentUser", 0);
        if(account.contains("id") && account.contains("token")){
            return true;
        }
        return false;
    }
}
