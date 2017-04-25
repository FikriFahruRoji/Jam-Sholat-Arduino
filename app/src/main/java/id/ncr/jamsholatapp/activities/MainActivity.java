package id.ncr.jamsholatapp.activities;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.ncr.jamsholatapp.fragments.BreakFragment;
import id.ncr.jamsholatapp.fragments.CorrectionFragment;
import id.ncr.jamsholatapp.fragments.GeneralFragment;
import id.ncr.jamsholatapp.fragments.InfoFragment;
import id.ncr.jamsholatapp.R;
import id.ncr.jamsholatapp.helper.BluetoothHelper;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter = null;
    public static BluetoothHelper mBluetooth = new BluetoothHelper();
    private String DEVICE_NAME = "HC-06";               // The name of the remote device (BlueSMIRF Gold)

    private Button btn_bluetooth;
    private TextView text_bluetooth_stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);              //force portrait screen
        setContentView(R.layout.activity_main);

        GeneralFragment generalFragment = new GeneralFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, generalFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btn_bluetooth = (Button)findViewById(R.id.button_bluetooth);                    //button bluetooth and Text header
        text_bluetooth_stat = (TextView)findViewById(R.id.text_bluetooth_status);

        // Check if Bluetooth is supported by the device
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            new AlertDialog.Builder(this)
                    .setMessage("Bluetooth is not supported by the device")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @SuppressWarnings("deprecation")
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .show();
        }

        btn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Bluetooth connection
                text_bluetooth_stat.setText("Try connecting to " + DEVICE_NAME);
                mBluetooth.Connect(DEVICE_NAME);
            }
        });

        mBluetooth.setBluetoothHelperListener(new BluetoothHelper.BluetoothHelperListener() {
            @Override
            public void onBluetoothHelperMessageReceived(BluetoothHelper bluetoothhelper, String message) {

            }
            @Override
            public void onBluetoothHelperConnectionStateChanged(BluetoothHelper bluetoothhelper, boolean isConnected) {
                if (isConnected) {
                    text_bluetooth_stat.setText("Connected");
                } else {
                    text_bluetooth_stat.setText("Disconnected");
                    mBluetooth.Connect(DEVICE_NAME);
                }
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_general:
                    GeneralFragment generalFragment = new GeneralFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, generalFragment).commit();
                    return true;
                case R.id.nav_break:
                    BreakFragment breakFragment = new BreakFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, breakFragment).commit();
                    return true;
                case R.id.nav_correction:
                    CorrectionFragment correctionFragment = new CorrectionFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, correctionFragment).commit();
                    return true;
                case R.id.nav_info:
                    InfoFragment infoFragment = new InfoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, infoFragment).commit();
                    return true;
            }
            return false;
        }
    };
}
