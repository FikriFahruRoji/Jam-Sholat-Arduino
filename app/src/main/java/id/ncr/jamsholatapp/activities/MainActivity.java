package id.ncr.jamsholatapp.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    Button btn_bluetooth;
    TextView text_bluetooth_stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_bluetooth = (Button)findViewById(R.id.button_bluetooth);
        text_bluetooth_stat = (TextView)findViewById(R.id.text_bluetooth_status);

        GeneralFragment generalFragment = new GeneralFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, generalFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

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
