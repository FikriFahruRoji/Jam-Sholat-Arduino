package id.ncr.jamsholatapp.activities;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import id.ncr.jamsholatapp.R;
import id.ncr.jamsholatapp.adapter.TabFragmentPager;
import id.ncr.jamsholatapp.helper.BluetoothHelper;
import id.ncr.jamsholatapp.helper.GpsHelper;

public class Main extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private static final int REQUEST_ENABLE_BT = 0;
    ArrayList<BluetoothDevice> devices;
    ArrayList<String> allDevices;
    private BluetoothDevice deviceToConnect;
    ArrayAdapter<String> devicesListAdapter;

    public static GpsHelper gps;

    BluetoothAdapter mBluetoothAdapter = null;
    public static BluetoothHelper mBluetooth = new BluetoothHelper();
    private String DEVICE_NAME = "";               // The name of the remote device (BlueSMIRF Gold)

    private Button btn_bluetooth;
    private TextView text_bluetooth_stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        text_bluetooth_stat = (TextView)findViewById(R.id.text_bluetooth_status);
        btn_bluetooth = (Button)findViewById(R.id.button_bluetooth);
        btn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBluetooth.isConnected()) {
                    mBluetooth.Disconnect();
                } else {
                    getPairedDevices();
                }
            }
        });

        viewPager = (ViewPager)findViewById(R.id.pager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);

        viewPager.setAdapter(new TabFragmentPager(getSupportFragmentManager()));

        tabLayout.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(android.R.color.white));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Check if Bluetooth is supported by the device
//        checkBluetoothSupported();

        final Drawable btDisable = this.getResources().getDrawable( R.drawable.ic_bluetooth_disabled_white_24dp);
        final Drawable bt = this.getResources().getDrawable( R.drawable.ic_bluetooth_white_24dp);

        mBluetooth.setBluetoothHelperListener(new BluetoothHelper.BluetoothHelperListener() {
            @Override
            public void onBluetoothHelperMessageReceived(BluetoothHelper bluetoothhelper, String message) {}
            @Override
            public void onBluetoothHelperConnectionStateChanged(BluetoothHelper bluetoothhelper, boolean isConnected) {

                if (isConnected) {
                    text_bluetooth_stat.setText("Connected to " + DEVICE_NAME);
                    btn_bluetooth.setText("Disconnect");
                    btn_bluetooth.setCompoundDrawablesWithIntrinsicBounds(btDisable, null, null, null);
                    Toast.makeText(Main.this, "Connected to " + DEVICE_NAME, Toast.LENGTH_SHORT).show();
                } else {
                    text_bluetooth_stat.setText("Disconnected");
                    btn_bluetooth.setText("Search");
                    btn_bluetooth.setCompoundDrawablesWithIntrinsicBounds(bt, null, null, null);
                    Toast.makeText(Main.this, "Bluetooth disconected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkBluetoothSupported(){
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
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private void getPairedDevices() {
        if (devices == null)
            devices = new ArrayList<BluetoothDevice>();
        else
            devices.clear();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice curDevice : pairedDevices) {
                devices.add(curDevice);
            }

            List<String> tempDevices = new ArrayList<String>();
            for (BluetoothDevice b : devices) {
                String paired = "Paired";
                if (b.getBondState() != 12) {
                    paired = "Not Paired";
                }
                tempDevices.add(b.getName() + " - [ " + paired + " ] ");
            }

            if (allDevices == null)
                allDevices = new ArrayList<String>();
            else
                allDevices.clear();

            allDevices.addAll(tempDevices);

            if (devicesListAdapter == null) {
                ListView devicesList = new ListView(this);
                devicesList.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                devicesListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, allDevices);
                devicesList.setAdapter(devicesListAdapter);
                //Create sequence of items
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setTitle("List Devices");
                dialogBuilder.setView(devicesList);
                //Create alert dialog object via builder
                final AlertDialog alertDialogObject = dialogBuilder.create();
                devicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        deviceToConnect = devices.get(position);
                        devicesListAdapter = null;
                        alertDialogObject.dismiss();
                        DEVICE_NAME = deviceToConnect.getName();
                        mBluetooth.Connect(DEVICE_NAME);
                    }
                });
                alertDialogObject.show();
                alertDialogObject.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        devicesListAdapter = null;
                    }
                });
            } else {
                devicesListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(getResources().getString(R.string.exit))
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @SuppressWarnings("deprecation")
                    public void onClick(DialogInterface dialog,int id) {
                        if (mBluetooth.isConnected()){
                            mBluetooth.Disconnect();
                        }
                        finish();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}