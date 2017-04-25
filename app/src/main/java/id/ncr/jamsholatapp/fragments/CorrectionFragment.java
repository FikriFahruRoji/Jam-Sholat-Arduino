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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import id.ncr.jamsholatapp.R;

public class CorrectionFragment extends Fragment {

    private Button btn_send_correction;
    private EditText tx_correct_isya, tx_correct_shubuh, tx_correct_syuruq, tx_correct_dzuhur, tx_correct_ashar, tx_correct_maghrib;
    private TextInputLayout layout_tx_correct_isya, layout_tx_correct_shubuh, layout_tx_correct_syuruq, layout_tx_correct_dzuhur, layout_tx_correct_ashar, layout_tx_correct_maghrib;
    private Spinner spin_isya, spin_shubuh, spin_syuruq, spin_dzuhur, spin_ashar, spin_maghrib;

    private String correct_isya, correct_shubuh, correct_syuruq, correct_dzuhur, correct_ashar, correct_maghrib;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_correction, container, false);

        layout_tx_correct_isya = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_correct_isya);
        tx_correct_isya = (EditText) viewRoot.findViewById(R.id.input_correct_isya);
        layout_tx_correct_shubuh = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_correct_shubuh);
        tx_correct_shubuh = (EditText) viewRoot.findViewById(R.id.input_correct_shubuh);
        layout_tx_correct_syuruq = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_correct_syuruq);
        tx_correct_syuruq = (EditText) viewRoot.findViewById(R.id.input_correct_syuruq);
        layout_tx_correct_dzuhur = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_correct_dzuhur);
        tx_correct_dzuhur = (EditText) viewRoot.findViewById(R.id.input_correct_dzuhur);
        layout_tx_correct_ashar = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_correct_ashar);
        tx_correct_ashar = (EditText) viewRoot.findViewById(R.id.input_correct_ashar);
        layout_tx_correct_maghrib = (TextInputLayout) viewRoot.findViewById(R.id.layout_input_correct_maghrib);
        tx_correct_maghrib = (EditText) viewRoot.findViewById(R.id.input_correct_maghrib);

        spin_isya = (Spinner) viewRoot.findViewById(R.id.spin_isya);
        spin_shubuh = (Spinner) viewRoot.findViewById(R.id.spin_shubuh);
        spin_syuruq = (Spinner) viewRoot.findViewById(R.id.spin_syuruq);
        spin_dzuhur = (Spinner) viewRoot.findViewById(R.id.spin_dzuhur);
        spin_ashar = (Spinner) viewRoot.findViewById(R.id.spin_ashar);
        spin_maghrib = (Spinner) viewRoot.findViewById(R.id.spin_maghrib);


        btn_send_correction = (Button) viewRoot.findViewById(R.id.btn_send_correction);
        btn_send_correction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateText(layout_tx_correct_isya, tx_correct_isya) &&
                        validateText(layout_tx_correct_shubuh, tx_correct_shubuh) &&
                        validateText(layout_tx_correct_syuruq, tx_correct_syuruq) &&
                        validateText(layout_tx_correct_dzuhur, tx_correct_dzuhur) &&
                        validateText(layout_tx_correct_ashar, tx_correct_ashar) &&
                        validateText(layout_tx_correct_maghrib, tx_correct_maghrib)){
                    correct_isya = spin_isya.getSelectedItem().toString() + tx_correct_isya.getText();
                    correct_shubuh = spin_shubuh.getSelectedItem().toString() + tx_correct_shubuh.getText();
                    correct_syuruq = spin_syuruq.getSelectedItem().toString() + tx_correct_syuruq.getText();
                    correct_dzuhur = spin_dzuhur.getSelectedItem().toString() + tx_correct_dzuhur.getText();
                    correct_ashar = spin_ashar.getSelectedItem().toString() + tx_correct_ashar.getText();
                    correct_maghrib = spin_maghrib.getSelectedItem().toString() + tx_correct_maghrib.getText();

                    Toast.makeText(getContext(), correct_isya + "\n" + correct_shubuh + "\n" + correct_syuruq + "\n" +
                            correct_dzuhur + "\n" + correct_ashar + "\n" + correct_maghrib, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return viewRoot;
    }

    private boolean validateText(TextInputLayout inputLayout, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            inputLayout.setError(getString(R.string.msg_error_input));
            editText.requestFocus();
            return false;
        } else {
            inputLayout.setErrorEnabled(false);
        }
        return true;
    }
}
