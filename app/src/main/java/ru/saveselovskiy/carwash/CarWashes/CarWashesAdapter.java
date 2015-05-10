package ru.saveselovskiy.carwash.CarWashes;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ru.saveselovskiy.carwash.Carwash.Carwash;
import ru.saveselovskiy.carwash.R;

/**
 * Created by Sergey on 04.05.2015.
 */
public class CarWashesAdapter extends BaseAdapter{


    private Activity activity;
    private Carwash[] data;
    private static LayoutInflater inflater=null;
//    public ArrayList<String> names;

    public CarWashesAdapter(Activity a, Carwash[] d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.washes_list_item, null);
        TextView name = (TextView)vi.findViewById(R.id.carwash_name);
        name.setText(data[position].name);
        TextView distance = (TextView)vi.findViewById(R.id.distance);
        distance.setText("7 км");
        distance.setGravity(Gravity.CENTER);
        TextView hours = (TextView)vi.findViewById(R.id.hours);
        hours.setText(data[position].from + " - " + data[position].to);

        return vi;
    }

}
