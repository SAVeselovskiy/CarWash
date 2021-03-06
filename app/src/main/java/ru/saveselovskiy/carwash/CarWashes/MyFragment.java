package ru.saveselovskiy.carwash.CarWashes;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ru.saveselovskiy.carwash.CarWashModel.CarWashesStorage;
import ru.saveselovskiy.carwash.Carwash.Carwash;
import ru.saveselovskiy.carwash.R;

/**
 * Created by sergejveselovskij on 01.05.15.
 */
public class MyFragment extends Fragment{

    GoogleMap mMap;
    MapFragment fragment;
    static MapFragment f;
    View rootView;
    int count = 0;
    LatLngBounds.Builder builder;
    Activity up;

    private static final String ARG_POSITION = "position";
    private int position;


    ArrayList<LatLng> geopoint_buildings = new ArrayList<LatLng>();
    String[] name_buildings;

    String[] address_buildings;

    ArrayList<String> names = new ArrayList<String>();

    String[] id_buildings;

    public MyFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getFragmentManager();
//        fragment = (MapFragment)fm.findFragmentById(R.id.map);
//        if (fragment == null) {
            fragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, fragment).commit();
//        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        FrameLayout fl = new FrameLayout(getActivity());
        fl.setLayoutParams(params);
        rootView = inflater.inflate(R.layout.map_fragment, container,
                false);
        up = this.getActivity();
        builder = new LatLngBounds.Builder();
        Carwash[] array = CarWashesStorage.getCarWashes();
        for (int i = 0; i < array.length; i++) {
            LatLng point = new LatLng(array[i].latitude, array[i].longitude);
            geopoint_buildings.add(i,point);
            names.add(i,array[i].name);
        }
        return rootView;
    }

    @Override
    public void onResume() {
//        ((ActionBarActivity) getActivity()).getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        super.onResume();
        if (mMap == null) {
            fragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    initDataArrays();
                    mMap.setOnInfoWindowClickListener(new listener());
                    loadInfo();
                }
            });

        }
    }

    public void showObject(LatLng coords, String name) {
        count--;
        builder.include(coords);
        mMap.addMarker(new MarkerOptions()
                        .position(coords)
                        .title(name)
        );
        if (count == 0) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 12));
        }
    }

    public class listener implements GoogleMap. OnInfoWindowClickListener {

        @Override
        public void onInfoWindowClick(Marker marker) {
            String id = "1";
            LatLng pos = marker.getPosition();
            for (int j = 0; j < geopoint_buildings.size(); j++);
//                if (pos.equals(geopoint_buildings)){
//                    id = id_buildings[j];
//                    break;
//                }
//            Intent myIntent = new Intent(up, AdvertisingIdClient.Info.class);
//            myIntent.putExtra("id","" + id);
//            startActivity(myIntent);
        }
    }

    public void loadInfo() {
        if (geopoint_buildings != null) {
            for (int j = 0; j < geopoint_buildings.size(); j++) {
                showObject(geopoint_buildings.get(j), names.get(j));
                count++;
            }
        }
        else {
            initDataArrays();
        }
    }

    public void initDataArrays() {
        switch (position) {
            case 0:
//                name_buildings = getActivity().getResources().getStringArray(R.array.educational_buildings);
//                address_buildings = getActivity().getResources().getStringArray(R.array.address_educational_buildings);
//                id_buildings = getActivity().getResources().getStringArray(R.array.id_educational_buildings);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.790908, 49.143495), 13));
                break;
            case 1:
//                name_buildings = getActivity().getResources().getStringArray(R.array.library);
//                address_buildings = getActivity().getResources().getStringArray(R.array.address_library);
//                id_buildings = getActivity().getResources().getStringArray(R.array.id_library);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.790908, 49.143495), 14));
                break;
            case 2:
//                name_buildings = getActivity().getResources().getStringArray(R.array.sports_complex);
//                address_buildings = getActivity().getResources().getStringArray(R.array.address_sports_complex);
//                id_buildings = getActivity().getResources().getStringArray(R.array.id_sports_complex);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.770732, 49.124870), 14));
                break;
            case 3:
//                name_buildings = getActivity().getResources().getStringArray(R.array.dorm);
//                address_buildings = getActivity().getResources().getStringArray(R.array.address_dorm);
//                id_buildings = getActivity().getResources().getStringArray(R.array.id_dorm);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.791590, 49.155718), 13));
                break;
            case 4:
//                name_buildings = getActivity().getResources().getStringArray(R.array.concert_halls);
//                address_buildings = getActivity().getResources().getStringArray(R.array.address_concert_halls);
//                id_buildings = getActivity().getResources().getStringArray(R.array.id_concert_halls);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.790833, 49.121668), 15));
                break;
            case 5:
//                name_buildings = getActivity().getResources().getStringArray(R.array.IT);
//                address_buildings = getActivity().getResources().getStringArray(R.array.address_IT);
//                id_buildings = getActivity().getResources().getStringArray(R.array.id_IT);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.790833, 49.121668), 15));
                break;
            case 6:
//                name_buildings = getActivity().getResources().getStringArray(R.array.museum);
//                address_buildings = getActivity().getResources().getStringArray(R.array.address_museum);
//                id_buildings = getActivity().getResources().getStringArray(R.array.id_museum);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.790833, 49.121668), 15));
                break;
        }
    }

}
