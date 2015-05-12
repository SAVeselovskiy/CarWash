package ru.saveselovskiy.carwash.CarWashes;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import ru.saveselovskiy.carwash.Carwash.TimetableListFragment;
import ru.saveselovskiy.carwash.MainActivity;
import ru.saveselovskiy.carwash.R;

/**
 * Created by Sergey on 04.05.2015.
 */
public class CarWashesList extends Fragment{
    public static CarWashesList newInstance(){
        return new CarWashesList();
    }

    private Carwash[] list;

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
                list = carWashes.carWashes;
                CarWashesStorage.setCarWashes(carWashes.carWashes);
            }

            @Override
            public void failure(RetrofitError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Не удалось обновить список." + error.getMessage())
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

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimetableListFragment timetable = new TimetableListFragment();
                timetable.carwash = list[position];
                MainActivity upActivity = (MainActivity)getActivity();
//                getActivity().getFragmentManager().beginTransaction().remove(upActivity.current).add(timetable,"timatable").commit();
            }
        });

        return rootView;
    }
}
