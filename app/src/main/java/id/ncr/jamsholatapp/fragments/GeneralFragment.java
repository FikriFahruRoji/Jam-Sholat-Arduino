package id.ncr.jamsholatapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import id.ncr.jamsholatapp.R;

public class GeneralFragment extends Fragment implements View.OnClickListener {

    private ImageButton btn_set_geo;
    private Button btn_send_geo, btn_send_name, btn_send_addres, btn_send_brights, btn_send_buzzer;
    private EditText tx_geo, tx_name, tx_addres;
    private TextInputLayout layout_tx_geo, layout_tx_name, layout_tx_addres;
    private RadioGroup rg_buzzer, rg_brights;
    private int brights_value = 2;
    private boolean buzzer_value = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_general, container, false);

        btn_set_geo = (ImageButton) viewRoot.findViewById(R.id.btn_set_geo);
        btn_set_geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        layout_tx_geo = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_geo);
        tx_geo = (EditText) viewRoot.findViewById(R.id.input_geo);
        layout_tx_name = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_nama_masjid);
        tx_name = (EditText) viewRoot.findViewById(R.id.input_nama_masjid);
        layout_tx_addres = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_alamat_masjid);
        tx_addres = (EditText) viewRoot.findViewById(R.id.input_alamat_masjid);

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
                        brights_value = 3;
                    } else if (checkedRadioButton.getText().equals(getString(R.string.sedang))) {
                        brights_value = 2;
                    } else {
                        brights_value = 1;
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
                        buzzer_value = true;
                    } else {
                        buzzer_value = false;
                    }
                }
            }
        });

        return viewRoot;
    }

    private boolean validateName(TextInputLayout inputLayout, EditText editText, String err_message) {
        if (editText.getText().toString().trim().isEmpty()) {
            inputLayout.setError(err_message);
            editText.requestFocus();
            return false;
        } else {
            inputLayout.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_send_geo:
                if (validateName(layout_tx_geo, tx_geo, "Lokasi tidak boleh kosong"))
                    Toast.makeText(getContext(), tx_geo.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send_masjid_name:
                if (validateName(layout_tx_name, tx_name, "Nama tidak boleh kosong"))
                    Toast.makeText(getContext(), tx_name.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send_masjid_addres:
                if (validateName(layout_tx_addres, tx_addres, "Alamat tidak boleh kosong"))
                    Toast.makeText(getContext(), tx_addres.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send_brights:
                Toast.makeText(getContext(), String.valueOf(brights_value), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send_buzzer:
                Toast.makeText(getContext(), String.valueOf(buzzer_value), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
