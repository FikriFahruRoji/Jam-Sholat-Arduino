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

import id.ncr.jamsholatapp.R;

public class CorrectionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_correction, container, false);

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
}
