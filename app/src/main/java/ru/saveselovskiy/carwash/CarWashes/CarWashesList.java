package ru.saveselovskiy.carwash.CarWashes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.saveselovskiy.carwash.CarWashModel.CarWashesStorage;
import ru.saveselovskiy.carwash.Carwash.Carwash;
import ru.saveselovskiy.carwash.CarwashAdapter.CarWashAdapter;
import ru.saveselovskiy.carwash.CarwashAdapter.CarWashes;
import ru.saveselovskiy.carwash.CarwashAdapter.CarwashesWorker;
import ru.saveselovskiy.carwash.R;

/**
 * Created by Sergey on 04.05.2015.
 */
public class CarWashesList extends Fragment{
    public static CarWashesList newInstance(){
        return new CarWashesList();
    }
    public CarWashesList(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Автомойки");
//        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ListView rootView = (ListView)inflater.inflate(R.layout.washes_list, container,
                false);
        if (CarWashesStorage.getCarWashes()!=null){
            Carwash[] carWashes = CarWashesStorage.getCarWashes();
            CarWashesAdapter adapter = new CarWashesAdapter(getActivity(), carWashes);
            rootView.setAdapter(adapter);
            CarWashesStorage.setCarWashes(carWashes);
            return rootView;
        }
        RestAdapter carWashAdapter = CarWashAdapter.getAdapter();
        CarwashesWorker carwashesWorker = carWashAdapter.create(CarwashesWorker.class);
        carwashesWorker.getWashes(new Callback<CarWashes>() {
            @Override
            public void success(CarWashes carWashes, Response response) {
                CarWashesAdapter adapter = new CarWashesAdapter(getActivity(), carWashes.carWashes);
                rootView.setAdapter(adapter);
                CarWashesStorage.setCarWashes(carWashes.carWashes);
            }

            @Override
            public void failure(RetrofitError error) {


            }
        });

        return rootView;
    }
}
