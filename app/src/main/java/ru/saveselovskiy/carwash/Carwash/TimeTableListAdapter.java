package ru.saveselovskiy.carwash.Carwash;

import android.app.Activity;
import android.content.Context;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        View vi=convertView;
        Log.d("myLog","i'm in get view");
        if(convertView==null)
            vi = inflater.inflate(R.layout.timetable_item, null);
        int hour = Integer.parseInt(getDate(data[position].date));
        TextView time = (TextView)vi.findViewById(R.id.timetable_time);
        time.setText(position + ":00");
        TextView info = (TextView)vi.findViewById(R.id.timetable_item_info);
        if (hour == position){
            vi.setBackgroundColor(Color.GRAY);

            info.setText("ЗАНЯТО");
            return vi;
        }
        vi.setBackgroundColor(Color.TRANSPARENT);
        info.setText("");

        return vi;
    }
}
