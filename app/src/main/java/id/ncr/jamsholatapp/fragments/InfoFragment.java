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
import android.widget.Toast;

import id.ncr.jamsholatapp.R;

public class InfoFragment extends Fragment implements View.OnClickListener{

    private Button btn_send_khutbah, btn_send_imam, btn_send_muadzin, btn_send_pengumuman;
    private EditText tx_layar_khutbah, tx_layar_imam, tx_layar_muadzin, tx_layar_pengumuman;
    private TextInputLayout layout_tx_layar_khutbah, layout_tx_layar_imam, layout_tx_layar_muadzin, layout_tx_layar_pengumuman;
    private String layar_khutbah = "", layar_imam = "", layar_muadzin = "", layar_pengumuman = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_info, container, false);

        btn_send_khutbah = (Button) viewRoot.findViewById(R.id.btn_send_khutbah);
        btn_send_khutbah.setOnClickListener(this);
        btn_send_imam = (Button) viewRoot.findViewById(R.id.btn_send_imam);
        btn_send_imam.setOnClickListener(this);
        btn_send_muadzin = (Button) viewRoot.findViewById(R.id.btn_send_muadzin);
        btn_send_muadzin.setOnClickListener(this);
        btn_send_pengumuman = (Button) viewRoot.findViewById(R.id.btn_send_pengumuman);
        btn_send_pengumuman.setOnClickListener(this);

        layout_tx_layar_khutbah = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_khutbah);
        tx_layar_khutbah = (EditText) viewRoot.findViewById(R.id.input_khutbah);
        layout_tx_layar_imam = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_imam);
        tx_layar_imam = (EditText) viewRoot.findViewById(R.id.input_imam);
        layout_tx_layar_muadzin = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_muadzin);
        tx_layar_muadzin = (EditText) viewRoot.findViewById(R.id.input_muadzin);
        layout_tx_layar_pengumuman = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_pengumuman);
        tx_layar_pengumuman = (EditText) viewRoot.findViewById(R.id.input_pengumuman);

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
            case R.id.btn_send_iqoma:

                break;
            default:
                break;
        }
    }
}
