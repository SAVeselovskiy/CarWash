package ru.saveselovskiy.carwash.Cars;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.saveselovskiy.carwash.R;

/**
 * Created by ellinakuznecova on 10.05.15.
 */
public class CarsAdapter  extends BaseAdapter {


    private Activity activity;
    private Car[] data;
    private static LayoutInflater inflater=null;
//    public ArrayList<String> names;

    public CarsAdapter(Activity a, Car[] d) {
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
            vi = inflater.inflate(R.layout.car_item, null);
        ImageView imageView = (ImageView)vi.findViewById(R.id.image);
        TextView tv = (TextView)vi.findViewById(R.id.tv);
        String [] cars = activity.getResources().getStringArray(R.array.cars);
        for ( int i = 0; i < cars.length; i++) {
            if (data[position].type.equals(cars[i])) {
                if (i == 0)
                    imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.mini));
                else if (i == 1)
                    imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.sedan));
                else if (i == 2)
                    imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.crossover));
                else if (i == 3)
                    imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.minibus));
                tv.setText(data[position].carNum + " " + data[position].model);
            }
        }

        return vi;
    }

}

