package ru.saveselovskiy.carwash.Cars;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.saveselovskiy.carwash.CarwashAdapter.CarWashAdapter;
//import ru.saveselovskiy.carwash.RestAdapter.CarwashesWorker;
import ru.saveselovskiy.carwash.CarwashAdapter.CarwashesWorker;
import ru.saveselovskiy.carwash.R;

/**
 * Created by ellinakuznecova on 10.05.15.
 */
public class CarsFragment extends Fragment {
    public static CarsFragment newInstance(){
        return new CarsFragment();
    }
    public CarsFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Мои машины");
//        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ListView rootView = (ListView)inflater.inflate(R.layout.cars_fragment, container,
                false);
        if (CarsStorage.cars!=null){
            Log.d("myLogs","in1");
            Car[] cars = CarsStorage.cars;
            CarsAdapter adapter = new CarsAdapter(getActivity(),cars);
            rootView.setAdapter(adapter);
            return rootView;
        }
        Log.d("myLogs","in2");
        SharedPreferences account = getActivity().getSharedPreferences("CurrentUser", 0);
        RestAdapter carWashAdapter = CarWashAdapter.getAdapter();
        CarwashesWorker carwashesWorker = carWashAdapter.create(CarwashesWorker.class);
        carwashesWorker.loadUsersCars(account.getInt("id",0),new Callback<Cars>() {
            @Override
            public void success(Cars cars, Response response) {
                Log.d("myLogs","in3");


                CarsStorage.setCars(cars.cars);
                CarsAdapter adapter = new CarsAdapter(getActivity(),cars.cars);
                rootView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs",error.getMessage());
            }
        });

        return rootView;
    }
}
