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

import java.util.Calendar;

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
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        String date = DateFormat.format("HH", cal).toString();
        return date;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        Log.d("myLog","i'm in get view");
        if(convertView==null)
            vi = inflater.inflate(R.layout.timetable, null);
        int hour = Integer.parseInt(getDate(data[position].date));
        TextView time = (TextView)vi.findViewById(R.id.timetable_time);
        time.setText(hour + ":00");
        if (hour != position){
            vi.setBackgroundColor(Color.GRAY);
            TextView info = (TextView)vi.findViewById(R.id.timetable_item_info);
            info.setText("5������, ���*!!!");
            return vi;
        }


        return vi;
    }
}
