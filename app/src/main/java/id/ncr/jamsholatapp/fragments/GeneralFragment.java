package id.ncr.jamsholatapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import id.ncr.jamsholatapp.R;

public class GeneralFragment extends Fragment implements View.OnClickListener {

    private ImageButton btn_set_geo;
    private Button btn_send_geo, btn_send_name, btn_send_addres, btn_send_brights, btn_send_buzzer;
    private EditText tx_geo, tx_name, tx_addres;
    private RadioButton rb_brights_low, rb_brights_normal, rb_brights_high, rb_buzzer_on, rb_buzzer_off;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_general, container, false);

        btn_set_geo = (ImageButton) viewRoot.findViewById(R.id.btn_set_geo);

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

        tx_geo = (EditText) viewRoot.findViewById(R.id.input_geo);
        tx_name = (EditText) viewRoot.findViewById(R.id.input_nama_masjid);
        tx_addres = (EditText) viewRoot.findViewById(R.id.input_alamat_masjid);

        rb_brights_low = (RadioButton) viewRoot.findViewById(R.id.rb_brights_low);
        rb_brights_normal = (RadioButton) viewRoot.findViewById(R.id.rb_brights_normal);
        rb_brights_high = (RadioButton) viewRoot.findViewById(R.id.rb_brights_high);

        rb_buzzer_on = (RadioButton) viewRoot.findViewById(R.id.rb_buzzer_on);
        rb_buzzer_off = (RadioButton) viewRoot.findViewById(R.id.rb_buzzer_off);

        return viewRoot;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_send_geo:
                Toast.makeText(getContext(), "geo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send_masjid_name:
                Toast.makeText(getContext(), "name", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send_masjid_addres:
                Toast.makeText(getContext(), "addres", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send_brights:
                Toast.makeText(getContext(), "brights", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send_buzzer:
                Toast.makeText(getContext(), "buzz", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
