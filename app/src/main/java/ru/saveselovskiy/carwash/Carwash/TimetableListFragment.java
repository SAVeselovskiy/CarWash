package ru.saveselovskiy.carwash.Carwash;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.drive.events.ProgressEvent;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.saveselovskiy.carwash.CarwashAdapter.CarWashAdapter;
import ru.saveselovskiy.carwash.CarwashAdapter.CarwashesWorker;
import ru.saveselovskiy.carwash.MainActivity;
import ru.saveselovskiy.carwash.R;

/**
 * Created by Sergey on 10.05.2015.
 */
public class TimetableListFragment extends Fragment{
    public Carwash carwash;
    public int id;
    public String name;
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
        final View timetableView = inflater.inflate(R.layout.timetable, container,
                false);
         records = (ListView)timetableView.findViewById(R.id.timetable_list);
        progressBar = (ProgressBar)timetableView.findViewById(R.id.progressBar);
        RestAdapter carWashAdapter = CarWashAdapter.getAdapter();
        records.setVisibility(View.INVISIBLE);
        records.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        records.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        CarwashesWorker carwashesWorker = carWashAdapter.create(CarwashesWorker.class);
        carwashesWorker.loadTimetable(carwash.id, new Callback<Timetable>() {
            @Override
            public void success(Timetable timetable, Response response) {
                progressBar.setVisibility(View.INVISIBLE);
                records.setVisibility(View.VISIBLE);

                TimeTableListAdapter adapter = new TimeTableListAdapter(getActivity(),timetable.recordArray);
                String[] array = {"Cat","Dog","Bird"};

                ArrayAdapter<String> adapterS = new ArrayAdapter<String>(getActivity(),R.layout.test_item,array);
                records.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Error." + error.getMessage())
                        .setMessage(error.getMessage())
                        .setCancelable(false)
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return timetableView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(carwash.name);
    }

}
