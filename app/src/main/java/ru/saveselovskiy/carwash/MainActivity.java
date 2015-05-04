package ru.saveselovskiy.carwash;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.google.android.gms.maps.model.LatLng;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.saveselovskiy.carwash.CarWashes.CarWashesList;
import ru.saveselovskiy.carwash.CarWashes.MyFragment;
import ru.saveselovskiy.carwash.CarwashAdapter.CarWashAdapter;
import ru.saveselovskiy.carwash.CarwashAdapter.CarWashes;
import ru.saveselovskiy.carwash.CarwashAdapter.CarwashesWorker;


public class MainActivity extends ActionBarActivity {
    private Drawer.Result drawerResult = null;
    final String MAP_TAG = "MAP";
    Fragment current;
    Fragment mapFragment;
    Fragment listFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Мои авто").withIdentifier(1),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Автомойки"),
                        new PrimaryDrawerItem().withName("История")).withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Скрываем клавиатуру при открытии Navigation Drawer
                        InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                }).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        if (i == 3) {
                            if(current instanceof MyFragment || current instanceof CarWashesList) return;
                            CarWashesList newList = new CarWashesList();
                            getFragmentManager().beginTransaction().add(R.id.parent_container,newList, "TAG").commit();
                            current = newList;
//                            listFragment = newList;
                        } else {
                            if (current != null) {
                                getFragmentManager().beginTransaction().remove(current).commit();
                                current = null;
                            }
                        }
                    }
                })
                .build();
        CarWashesList newList = new CarWashesList();
        getFragmentManager().beginTransaction().add(R.id.parent_container,newList, "TAG").commit();
        current = newList;
    }

    public void onBackPressed() {
        // Закрываем Navigation Drawer по нажатию системной кнопки "Назад" если он открыт
        if (drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.map_switcher) {
            if (current instanceof  MyFragment){
//                if (listFragment == null) {
//                    getFragmentManager().beginTransaction().detach(current).commit();
                    CarWashesList newList = new CarWashesList();
                    getFragmentManager().beginTransaction().remove(current).add(R.id.parent_container, newList, "TAG").commit();
                    current = newList;
//                    listFragment = newList;
//                }
//                else{
//                    getFragmentManager().beginTransaction().detach(current).attach(listFragment).commit();
//                    current = listFragment;
//                }
            }
            else{
//                if (mapFragment == null) {

                    MyFragment newFragment = new MyFragment();
                    getFragmentManager().beginTransaction().detach(current).add(R.id.parent_container, newFragment, MAP_TAG).commit();
                    current = newFragment;
//                    mapFragment = newFragment;
//                }
//                else{
//                    getFragmentManager().beginTransaction().detach(current).attach(mapFragment).commit();
//                    current = mapFragment;
//                }
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
