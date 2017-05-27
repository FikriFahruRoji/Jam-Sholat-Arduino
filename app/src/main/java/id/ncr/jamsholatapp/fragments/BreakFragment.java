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

import static id.ncr.jamsholatapp.activities.Main.mBluetooth;


public class BreakFragment extends Fragment implements View.OnClickListener {

    private Button btn_send_iqoma, btn_send_layar;
    private EditText tx_iqoma_shubuh, tx_iqoma_zhuhur, tx_iqoma_ashar, tx_iqoma_maghrib, tx_iqoma_isya, tx_layar_shubuh, tx_layar_zhuhur, tx_layar_ashar, tx_layar_maghrib, tx_layar_isya, tx_layar_jumat;
    private TextInputLayout layout_tx_iqoma_shubuh, layout_tx_iqoma_zhuhur, layout_tx_iqoma_ashar, layout_tx_iqoma_maghrib, layout_tx_iqoma_isya, layout_tx_layar_shubuh, layout_tx_layar_zhuhur, layout_tx_layar_ashar, layout_tx_layar_maghrib, layout_tx_layar_isya, layout_tx_layar_jumat;

    private String iqoma_isya = "", iqoma_shubuh = "", iqoma_zhuhur = "", iqoma_ashar = "", iqoma_maghrib = "", layar_isya = "", layar_shubuh = "", layar_zhuhur = "", layar_ashar = "", layar_maghrib = "", layar_jumat = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_break, container, false);

        btn_send_iqoma = (Button) viewRoot.findViewById(R.id.btn_send_iqoma);
        btn_send_iqoma.setOnClickListener(this);
        btn_send_layar = (Button) viewRoot.findViewById(R.id.btn_send_layar);
        btn_send_layar.setOnClickListener(this);

        layout_tx_iqoma_isya = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_iqoma_isya);
        tx_iqoma_isya = (EditText) viewRoot.findViewById(R.id.input_iqoma_isya);
        layout_tx_iqoma_shubuh = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_iqoma_shubuh);
        tx_iqoma_shubuh = (EditText) viewRoot.findViewById(R.id.input_iqoma_shubuh);
        layout_tx_iqoma_zhuhur = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_iqoma_zhuhur);
        tx_iqoma_zhuhur= (EditText) viewRoot.findViewById(R.id.input_iqoma_zhuhur);
        layout_tx_iqoma_ashar = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_iqoma_ashar);
        tx_iqoma_ashar = (EditText) viewRoot.findViewById(R.id.input_iqoma_ashar);
        layout_tx_iqoma_maghrib = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_iqoma_maghrib);
        tx_iqoma_maghrib = (EditText) viewRoot.findViewById(R.id.input_iqoma_maghrib);

        layout_tx_layar_isya = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_layar_isya);
        tx_layar_isya = (EditText) viewRoot.findViewById(R.id.input_layar_isya);
        layout_tx_layar_shubuh = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_layar_shubuh);
        tx_layar_shubuh = (EditText) viewRoot.findViewById(R.id.input_layar_shubuh);
        layout_tx_layar_zhuhur = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_layar_zhuhur);
        tx_layar_zhuhur= (EditText) viewRoot.findViewById(R.id.input_layar_zhuhur);
        layout_tx_layar_ashar = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_layar_ashar);
        tx_layar_ashar = (EditText) viewRoot.findViewById(R.id.input_layar_ashar);
        layout_tx_layar_maghrib = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_layar_maghrib);
        tx_layar_maghrib = (EditText) viewRoot.findViewById(R.id.input_layar_maghrib);
        layout_tx_layar_jumat = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_layar_jumat);
        tx_layar_jumat = (EditText) viewRoot.findViewById(R.id.input_layar_jumat);

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
        String messageCommand = "";
        switch (view.getId()) {
            case R.id.btn_send_iqoma:
                if (validateName(layout_tx_iqoma_shubuh, tx_iqoma_shubuh) &&
                        validateName(layout_tx_iqoma_zhuhur, tx_iqoma_zhuhur) &&
                        validateName(layout_tx_iqoma_ashar, tx_iqoma_ashar) &&
                        validateName(layout_tx_iqoma_maghrib, tx_iqoma_maghrib) &&
                        validateName(layout_tx_iqoma_isya, tx_iqoma_isya)) {

                    iqoma_isya = String.valueOf(tx_iqoma_isya.getText());
                    iqoma_shubuh = String.valueOf(tx_iqoma_shubuh.getText());
                    iqoma_zhuhur = String.valueOf(tx_iqoma_zhuhur.getText());
                    iqoma_ashar = String.valueOf(tx_iqoma_ashar.getText());
                    iqoma_maghrib = String.valueOf(tx_iqoma_maghrib.getText());

                    messageCommand = "*9|" + iqoma_shubuh + "|" + iqoma_zhuhur + "|" + iqoma_ashar + "|" + iqoma_maghrib + "|" + iqoma_isya + "#";
//                  TODO Sending bluetooth command
                    sendBluetoothMessage(messageCommand);
                }
                break;

            case R.id.btn_send_layar:
                if (validateName(layout_tx_layar_shubuh, tx_layar_shubuh) &&
                        validateName(layout_tx_layar_zhuhur, tx_layar_zhuhur) &&
                        validateName(layout_tx_layar_ashar, tx_layar_ashar) &&
                        validateName(layout_tx_layar_maghrib, tx_layar_maghrib) &&
                        validateName(layout_tx_layar_jumat, tx_layar_jumat) &&
                        validateName(layout_tx_layar_isya, tx_layar_isya)) {

                    layar_shubuh = String.valueOf(tx_layar_shubuh.getText());
                    layar_zhuhur = String.valueOf(tx_layar_zhuhur.getText());
                    layar_ashar = String.valueOf(tx_layar_ashar.getText());
                    layar_maghrib = String.valueOf(tx_layar_maghrib.getText());
                    layar_isya = String.valueOf(tx_layar_isya.getText());
                    layar_jumat = String.valueOf(tx_layar_jumat.getText());

                    messageCommand = "*10|" + layar_shubuh + "|" + layar_zhuhur + "|" + layar_zhuhur + "|" + layar_maghrib + "|" + layar_isya + "|" + layar_jumat + "#";
//                  TODO Sending bluetooth command
                    sendBluetoothMessage(messageCommand);
                }
                break;

            default:
                break;
        }
    }
}