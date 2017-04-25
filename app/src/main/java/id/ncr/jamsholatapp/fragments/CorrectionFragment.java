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

import id.ncr.jamsholatapp.R;

public class CorrectionFragment extends Fragment {

    private Button btn_send_correction;
    private EditText tx_correct_isya, tx_correct_shubuh, tx_correct_syuruq, tx_correct_dzuhur, tx_correct_ashar, tx_correct_maghrib;
    private TextInputLayout layout_tx_correct_isya, layout_tx_correct_shubuh, layout_tx_correct_syuruq, layout_tx_correct_dzuhur, layout_tx_correct_ashar, layout_tx_correct_maghrib;
    private RadioButton rg_isya_p, rg_isya_m, rg_shubuh_p, rg_shubuh_m, rg_syuruq_p, rg_syuruq_m, rg_dzuhur_p, rg_dzuhur_m, rg_ashar_p, rg_ashar_m, rg_maghrib_p, rg_maghrib_m;

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


        btn_send_correction = (Button) viewRoot.findViewById(R.id.btn_send_correction);
        btn_send_correction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private void rbcheck(RadioGroup radioGroup, int aa){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    if (checkedRadioButton.getText().equals("-")) {
                    }
                }
            }
        });
        aa = 7;
    }
}
