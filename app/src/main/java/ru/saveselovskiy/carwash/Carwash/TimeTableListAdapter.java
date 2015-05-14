package ru.saveselovskiy.carwash.Carwash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.saveselovskiy.carwash.CarWashes.CarWashesAdapter;
import ru.saveselovskiy.carwash.CarwashAdapter.CarWashAdapter;
import ru.saveselovskiy.carwash.CarwashAdapter.CarwashesWorker;
import ru.saveselovskiy.carwash.R;

/**
 * Created by Sergey on 10.05.2015.
 */
public class TimeTableListAdapter extends BaseAdapter{
    private Activity activity;
    private Record[] data;
    private static LayoutInflater inflater=null;
//    public ArrayList<String> names;

    public TimeTableListAdapter(Activity a, Record[] d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return 24;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    public static String getDate(long time)
    {
        Date date = new Date(time); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("HH"); // the format of your date
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vi= inflater.inflate(R.layout.timetable_item, null);
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi.setBackgroundColor(Color.BLUE);
                RestAdapter carWashAdapter = CarWashAdapter.getAdapter();
                CarwashesWorker carwashesWorker = carWashAdapter.create(CarwashesWorker.class);
                carwashesWorker.postRecord(1, "2015-05-14 13:00:00", "stark99", "3", "2", new Callback< Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        vi.setBackgroundColor(Color.MAGENTA);
                        Toast toast = Toast.makeText(activity,"Запись проведена успешно",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        vi.setBackgroundColor(Color.TRANSPARENT);
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
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
            }
        });
        TextView time = (TextView)vi.findViewById(R.id.timetable_time);
        time.setText(position + ":00");
        TextView info = (TextView)vi.findViewById(R.id.timetable_item_info);
        for (Record record : data) {
            int hour = Integer.parseInt(getDate(record.date));
            if (hour == position){
                vi.setBackgroundColor(Color.GRAY);

                info.setText("ЗАНЯТО");
                return vi;
            }
        }

        vi.setBackgroundColor(Color.TRANSPARENT);
        info.setText("");

        return vi;
    }
}
