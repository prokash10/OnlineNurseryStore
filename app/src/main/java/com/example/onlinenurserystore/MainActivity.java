package com.example.onlinenurserystore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.onlinenurserystore.ui.FavourteFragment;
import com.example.onlinenurserystore.ui.OrderhistoryFragment;
import com.example.onlinenurserystore.ui.account.AccountFragment;
import com.example.onlinenurserystore.ui.Favourities.CartFragment;
import com.example.onlinenurserystore.ui.dashboard.DashboardFragment;
import com.example.onlinenurserystore.ui.home.HomeFragment;
import com.example.onlinenurserystore.ui.order.OrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    private Sensor mProximity, mAcclerometer, gyroscopeSensor;
    private static final int SENSOR_SENSITIVITY = 4;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {
            String name = bundle.getString("name");
            if (name.equals("home")) {
                bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
                openFragment(new HomeFragment());
            }
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAcclerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor =
                mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ALL),
                SensorManager.SENSOR_DELAY_NORMAL);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_fav:
                    selectedFragment = new FavourteFragment();
                    break;
                case R.id.nav_history:
                    selectedFragment = new OrderhistoryFragment();
                    break;
                case R.id.nav_account:
                    selectedFragment = new AccountFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            return true;
        }
    };

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
                float delta = mAccelCurrent - mAccelLast;
                mAccel = mAccel * 0.9f + delta;
                if (mAccel > 10) {
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("User", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear().apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                    Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                }
            }
            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
                if(event.values[1] > 1.5f) { // anticlockwise
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                } else if(event.values[1] < -1.5f) { // clockwise
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

};

@Override
protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
        SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
        SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
        }

@Override
protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
        }
        }
