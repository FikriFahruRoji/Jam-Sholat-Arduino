package id.ncr.jamsholatapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.ncr.jamsholatapp.helper.GpsHelper;
import id.ncr.jamsholatapp.R;

import static id.ncr.jamsholatapp.activities.Main.gps;
import static id.ncr.jamsholatapp.activities.Main.mBluetooth;


public class GeneralFragment extends Fragment implements View.OnClickListener {

    private ImageButton btn_set_geo;
    private Button btn_send_geo, btn_send_name, btn_send_addres, btn_send_brights, btn_send_buzzer, btn_send_gmt, btn_send_time;
    private EditText tx_geo_long, tx_geo_lat, tx_name, tx_addres, tx_time;
    private TextInputLayout layout_tx_geo_long, layout_tx_geo_lat, layout_tx_name, layout_tx_addres, layout_tx_time;
    private RadioGroup rg_buzzer, rg_brights;
    private TextView tx_time_zone;
    
    private LinearLayout layout_gmt;
    private DigitalClock clock;

    private String geo, nama_masjid = "", alamat_masjid = "", brights_value = "150", buzzer_value = "0", time_zone = "+7", time = "";
    private double longitude = 0.0, latitude = 0.0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_general, container, false);

        tx_time_zone = (TextView) viewRoot.findViewById(R.id.tx_time_zone);
        layout_gmt = (LinearLayout) viewRoot.findViewById(R.id.layout_time_zone);
        layout_gmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {
                        "GMT-11:00 Midway",
                        "GMT-10:00 Honolulu",
                        "GMT-8:00 Anchorage",
                        "GMT-7:00 Los Angeles",
                        "GMT-6:00 Chihuahua",
                        "GMT-5:00 Chicago",
                        "GMT-4:00 New York",
                        "GMT-3:00 Sao Paulo",
                        "GMT-2:00 Georgia Selatan",
                        "GMT-1:00 Tanjung Verde",
                        "GMT-0:00 Casablanca",
                        "GMT+1:00 London",
                        "GMT+2:00 Amsterdam",
                        "GMT+3:00 Kairo",
                        "GMT+4:00 Dubai",
                        "GMT+5:00 Karachi",
                        "GMT+6:00 Almaty",
                        "GMT+7:00 Waktu Indonesia Barat",
                        "GMT+8:00 Waktu Indonesia Tengah",
                        "GMT+9:00 Waktu Indonesia Timur",
                        "GMT+10:00 Sydney",
                        "GMT+12:00 Fiji",
                        "GMT+13:00 Tongatapu"
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Time Zone");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String string = String.valueOf(items[item]);
                        tx_time_zone.setText(String.valueOf(items[item]));
                        string = string.substring(0, 6);
                        string = string.replace("GMT", "");
                        string = string.replace("+", "");
                        string = string.replace(":", "");
                        time_zone = string;
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btn_set_geo = (ImageButton) viewRoot.findViewById(R.id.btn_set_geo);
        btn_set_geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GpsHelper(getContext());
                if(gps.canGetLocation()){
                    longitude = gps.getLongitude();
                    latitude = gps.getLatitude();

                    if (longitude != 0.0) {
                        Toast.makeText(getContext(), "Location found", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "Your location :\n" + String.valueOf(longitude) + "\n" + String.valueOf(latitude), Toast.LENGTH_SHORT).show();
                        tx_geo_long.setText(String.valueOf(longitude).substring(0,8));
                        tx_geo_lat.setText(String.valueOf(latitude).substring(0,7));
                    } else {
                        tx_geo_long.setText("");
                        tx_geo_lat.setText("");
                        Toast.makeText(getContext(), "Failed to get location, Please try again", Toast.LENGTH_SHORT).show();
                    }
//                    tx_geo.setText(String.valueOf(longitude).substring(0,8) + ";" + String.valueOf(latitude).substring(0,7));
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

        btn_send_geo = (Button) viewRoot.findViewById(R.id.btn_send_geo);
        btn_send_geo.setOnClickListener(this);
        btn_send_name = (Button) viewRoot.findViewById(R.id.btn_send_masjid_name);
        btn_send_name.setOnClickListener(this);
        btn_send_addres = (Button) viewRoot.findViewById(R.id.btn_send_masjid_addres);
        btn_send_addres.setOnClickListener(this);
        btn_send_brights = (Button) viewRoot.findViewById(R.id.btn_send_brights);
        btn_send_brights.setOnClickListener(this);
        btn_send_buzzer = (Button) viewRoot.findViewById(R.id.btn_send_buzzer);
        btn_send_buzzer.setOnClickListener(this);
        btn_send_gmt = (Button) viewRoot.findViewById(R.id.btn_send_gmt);
        btn_send_gmt.setOnClickListener(this);
        btn_send_time = (Button) viewRoot.findViewById(R.id.btn_send_time);
        btn_send_time.setOnClickListener(this);

        layout_tx_geo_long = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_geo_long);
        tx_geo_long = (EditText) viewRoot.findViewById(R.id.input_geo_long);
        layout_tx_geo_lat = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_geo_lat);
        tx_geo_lat = (EditText) viewRoot.findViewById(R.id.input_geo_lat);
        layout_tx_name = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_nama_masjid);
        tx_name = (EditText) viewRoot.findViewById(R.id.input_nama_masjid);
        layout_tx_addres = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_alamat_masjid);
        tx_addres = (EditText) viewRoot.findViewById(R.id.input_alamat_masjid);

        layout_tx_time = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_jam);
        tx_time = (EditText) viewRoot.findViewById(R.id.input_jam);
        clock = (DigitalClock) viewRoot.findViewById(R.id.clock);
        clock.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                tx_time.setText(s.toString());
            }
        });

        rg_brights = (RadioGroup) viewRoot.findViewById(R.id.rg_brights);
        rg_brights.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    if (checkedRadioButton.getText().equals(getString(R.string.terang))) {
                        brights_value = "255";
                    } else if (checkedRadioButton.getText().equals(getString(R.string.sedang))) {
                        brights_value = "150";
                    } else {
                        brights_value = "100";
                    }
                }
            }
        });

        rg_buzzer = (RadioGroup) viewRoot.findViewById(R.id.rg_buzzer);
        rg_buzzer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    if (checkedRadioButton.getText().equals(getString(R.string.hidup))) {
                        buzzer_value = "1";
                    } else {
                        buzzer_value = "0";
                    }
                }
            }
        });

        return viewRoot;
    }

    private boolean validateName(TextInputLayout inputLayout, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            inputLayout.setError(getString(R.string.msg_error_input));
            editText.requestFocus();
            return false;
        } else {
            inputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void sendBluetoothMessage(String message){
        if (mBluetooth.isConnected()) {
            mBluetooth.SendMessage(message);
        } else {
            Toast.makeText(getContext(), getString(R.string.msg_error_connect), Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_send_geo:
                if (validateName(layout_tx_geo_long, tx_geo_long)== false || validateName(layout_tx_geo_lat, tx_geo_lat)==false) {

                } else {
//                    TODO Sending bluetooth command
                    geo = "*6|" + String.valueOf(tx_geo_long.getText()) + "|" + String.valueOf(tx_geo_lat.getText()) + "# ";
                    sendBluetoothMessage(geo);
                }
                break;

            case R.id.btn_send_masjid_name:
                if (validateName(layout_tx_name, tx_name)) {
                    nama_masjid = "*1|" + String.valueOf(tx_name.getText() + "#");
//                  TODO Sending bluetooth command
                    sendBluetoothMessage(nama_masjid);
                }
                break;

            case R.id.btn_send_masjid_addres:
                if (validateName(layout_tx_addres, tx_addres)) {
                    alamat_masjid = "*2|" + String.valueOf(tx_addres.getText()) + "#";
//                  TODO Sending bluetooth command
                    sendBluetoothMessage(alamat_masjid);
                }
                break;

            case R.id.btn_send_brights:
//              TODO Sending bluetooth command
                sendBluetoothMessage("*4|" + brights_value + "#");
                break;

            case R.id.btn_send_buzzer:
//              TODO Sending bluetooth command
                sendBluetoothMessage("*11|" + buzzer_value + "#");
                break;

            case R.id.btn_send_gmt:
//              TODO Sending bluetooth command
                sendBluetoothMessage("*7|" + time_zone + "#");
                break;

            case R.id.btn_send_time:
//              TODO Sending bluetooth command
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                time = sdf.format(new Date());
                sendBluetoothMessage("*13|" + time + "#");
                break;

            default:
                break;
        }
    }

}
