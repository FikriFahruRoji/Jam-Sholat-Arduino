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
    private EditText tx_khutbah, tx_imam, tx_muadzin, tx_pengumuman;
    private TextInputLayout layout_tx_khutbah, layout_tx_imam, layout_tx_muadzin, layout_tx_pengumuman;

    private String khutbah = "", imam = "", muadzin = "", pengumuman = "";

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

        layout_tx_khutbah = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_khutbah);
        tx_khutbah = (EditText) viewRoot.findViewById(R.id.input_khutbah);
        layout_tx_imam = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_imam);
        tx_imam = (EditText) viewRoot.findViewById(R.id.input_imam);
        layout_tx_muadzin = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_muadzin);
        tx_muadzin = (EditText) viewRoot.findViewById(R.id.input_muadzin);
        layout_tx_pengumuman = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_pengumuman);
        tx_pengumuman = (EditText) viewRoot.findViewById(R.id.input_pengumuman);

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

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_send_khutbah:
                if (validateName(layout_tx_khutbah, tx_khutbah)) {
                    khutbah = String.valueOf(tx_khutbah.getText());
                    Toast.makeText(getContext(), khutbah, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_send_imam:
                if (validateName(layout_tx_imam, tx_imam)) {
                    imam = String.valueOf(tx_imam.getText());
                    Toast.makeText(getContext(), imam, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_send_muadzin:
                if (validateName(layout_tx_muadzin, tx_muadzin)) {
                    muadzin = String.valueOf(tx_muadzin.getText());
                    Toast.makeText(getContext(), muadzin, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_send_pengumuman:
                if (validateName(layout_tx_pengumuman, tx_pengumuman)) {
                    pengumuman = String.valueOf(tx_pengumuman.getText());
                    Toast.makeText(getContext(), pengumuman, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
