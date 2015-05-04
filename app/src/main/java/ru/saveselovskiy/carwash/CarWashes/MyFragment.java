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


    LatLng[][] geopoint_buildings = {
            {
                    new LatLng(55.790843,49.121839), new LatLng(55.790918,49.119216), new LatLng(55.791714,49.119677),
                    new LatLng(55.791535,49.120342), new LatLng(55.789713,49.120220), new LatLng(55.789576,49.121503),
                    new LatLng(55.790305,49.119814), new LatLng(55.791436,49.119374), new LatLng(55.794076,49.114069),
                    new LatLng(55.793938,49.113179), new LatLng(55.786625,49.126034), new LatLng(55.761985,49.149218),
                    new LatLng(55.791835,49.117663), new LatLng(55.792129,49.122164), new LatLng(55.792456,49.122475),
                    new LatLng(55.792634,49.119939), new LatLng(55.793326,49.143072), new LatLng(55.793370,49.143328),
                    new LatLng(55.787961,49.112449), new LatLng(55.784347,49.116633), new LatLng(55.789950,49.108446),
                    new LatLng(55.787454,49.110386), new LatLng(55.785283,49.118412), new LatLng(55.789598,49.158523),
                    new LatLng(55.822101,49.098264), new LatLng(55.793459,49.120393), new LatLng(55.791571,49.122873),
                    new LatLng(55.789934,49.121850), new LatLng(55.789928,49.120686)
            },
            {
                    new LatLng(55.790867,49.121813), new LatLng(55.794284,49.113565), new LatLng(55.786513,49.126308),
                    new LatLng(55.791859,49.117667), new LatLng(55.792468,49.122351), new LatLng(55.792640,49.119869),
                    new LatLng(55.790139,49.165480), new LatLng(55.792043,49.126202), new LatLng(55.793858,49.143994),
                    new LatLng(55.787971,49.112440), new LatLng(55.784356,49.116623), new LatLng(55.787453,49.110399)
            },
            {
                    new LatLng(55.791197,49.124089), new LatLng(55.742509,49.122628), new LatLng(55.793849,49.143935),
                    new LatLng(55.788928,49.108037)
            },
            {
                    new LatLng(55.788399,49.164457), new LatLng(55.789187,49.165110), new LatLng(55.785532,49.163392),
                    new LatLng(55.790151,49.165469), new LatLng(55.785439,49.170386), new LatLng(55.785840,49.163399),
                    new LatLng(55.791145,49.126074), new LatLng(55.792040,49.126202), new LatLng(55.805240,49.189937),
                    new LatLng(55.786404,49.128802)
            },
            {
                    new LatLng(55.790855,49.121786),new LatLng(55.791185,49.124105)
            },
            {
                    new LatLng(55.791568,49.122867)
            },
            {
                    new LatLng(55.790843,49.121839)
            }
    };
    String[] name_buildings;

    String[] address_buildings;

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
        return rootView;
    }

    @Override
    public void onResume() {
        ((ActionBarActivity) getActivity()).getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
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

    public void showObject(LatLng coords) {
        count--;
        builder.include(coords);
        mMap.addMarker(new MarkerOptions()
                        .position(coords)
                        .title("test")
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
            for (int j = 0; j < geopoint_buildings[position].length; j++)
                if (pos.equals(geopoint_buildings[position][j])){
                    id = id_buildings[j];
                    break;
                }
            Intent myIntent = new Intent(up, AdvertisingIdClient.Info.class);
            myIntent.putExtra("id","" + id);
            startActivity(myIntent);
        }
    }

    public void loadInfo() {
        if (geopoint_buildings != null) {
            for (int j = 0; j < geopoint_buildings[position].length; j++) {
                showObject(geopoint_buildings[position][j]);
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
