package com.example.baobao.myapp;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by wangyun on 15/10/19.
 */
public class WebAction extends Activity {

    private TextView positionTextView;
    private LocationManager locationManager;
    private String provider;
    static int a=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hand);
        positionTextView = (TextView) findViewById(R.id.position_text_view);
        locationManager = (LocationManager) getSystemService(Context.
                LOCATION_SERVICE);
// 获取所有可用的位置
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
// 当没有可用的位置,弹出Toast
            Toast.makeText(this, "No location provider to use",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
// 显示当前设备的位置信息
            showLocation(location);
        }
        locationManager.requestLocationUpdates(provider, 500, 1,
                locationListener);
        Button b = (Button)findViewById(R.id.bb);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextToast("我来了"+a);
                a++;
            }
        });

    }

    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
// 关闭程序时将监听器移除
            locationManager.removeUpdates(locationListener);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle
                extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
// 更新当前设备的位置信息
            showLocation(location);
        }
    };

    private void showLocation(Location location) {
        String currentPosition = "latitude is " + location.getLatitude() + "\n"
                + "longitude is " + location.getLongitude();
        positionTextView.setText(currentPosition);
    }
    Toast toast;
    private void showTextToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }


}
