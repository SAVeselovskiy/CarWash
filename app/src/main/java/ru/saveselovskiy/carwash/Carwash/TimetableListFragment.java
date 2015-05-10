package ru.saveselovskiy.carwash.Carwash;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.saveselovskiy.carwash.CarwashAdapter.CarWashAdapter;
import ru.saveselovskiy.carwash.CarwashAdapter.CarwashesWorker;
import ru.saveselovskiy.carwash.R;

/**
 * Created by Sergey on 10.05.2015.
 */
public class TimetableListFragment extends Fragment{
    public Carwash carwash;
    private ListView records;
    private ProgressBar progressBar;
    public static TimetableListFragment newInstance(){
        return new TimetableListFragment();
    }
    public TimetableListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View timetable = super.onCreateView(inflater, container, savedInstanceState);
        progressBar = (ProgressBar)timetable.findViewById(R.id.timetable_progress);
        records = (ListView)timetable.findViewById(R.id.timetable_list);
        RestAdapter carWashAdapter = CarWashAdapter.getAdapter();
        records.setVisibility(View.GONE);

        CarwashesWorker carwashesWorker = carWashAdapter.create(CarwashesWorker.class);
        carwashesWorker.loadTimetable(carwash.id, new Callback<Timetable>() {
            @Override
            public void success(Timetable timetable, Response response) {
                progressBar.setVisibility(View.GONE);
                TimeTableListAdapter adapter = new TimeTableListAdapter(getActivity(),timetable.recordArray);
                records.setAdapter(adapter);
                records.setVisibility(View.VISIBLE);
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

        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(carwash.name);
    }

}
