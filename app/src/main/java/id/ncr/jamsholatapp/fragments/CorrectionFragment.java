package id.ncr.jamsholatapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import id.ncr.jamsholatapp.R;

import static id.ncr.jamsholatapp.activities.Main.mBluetooth;

public class CorrectionFragment extends Fragment {

    private Button btn_send_correction;
    private TextView menit_shubuh, menit_syuruq, menit_zhuhur, menit_ashar, menit_maghrib, menit_isya;
    private SeekBar seek_shubuh, seek_syuruq, seek_zhuhur, seek_ashar, seek_maghrib, seek_isya;
    private String correct_shubuh = "+0", correct_syuruq = "+0", correct_zhuhur = "+0", correct_ashar = "+0", correct_maghrib = "+0", correct_isya = "+0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_correction, container, false);

        seek_shubuh = (SeekBar) viewRoot.findViewById(R.id.seek_shubuh);
        menit_shubuh = (TextView) viewRoot.findViewById(R.id.correction_shubuh);
        seek_shubuh.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                correct_shubuh = String.valueOf(progress-10);
                if ((progress - 10) < 0){
                    menit_shubuh.setText(String.valueOf(progress-10) + " Menit");
                } else {
                    menit_shubuh.setText("+" + String.valueOf(progress-10) + " Menit");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seek_syuruq = (SeekBar) viewRoot.findViewById(R.id.seek_syuruq);
        menit_syuruq = (TextView) viewRoot.findViewById(R.id.correction_syuruq);
        seek_syuruq.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                correct_syuruq = String.valueOf(progress-10);
                if ((progress - 10) < 0){
                    menit_syuruq.setText(String.valueOf(progress-10) + " Menit");
                } else {
                    menit_syuruq.setText("+" + String.valueOf(progress-10) + " Menit");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seek_zhuhur = (SeekBar) viewRoot.findViewById(R.id.seek_zhuhur);
        menit_zhuhur = (TextView) viewRoot.findViewById(R.id.correction_zhuhur);
        seek_zhuhur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                correct_zhuhur = String.valueOf(progress-10);
                if ((progress - 10) < 0){
                    menit_zhuhur.setText(String.valueOf(progress-10) + " Menit");
                } else {
                    menit_zhuhur.setText("+" + String.valueOf(progress-10) + " Menit");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seek_ashar = (SeekBar) viewRoot.findViewById(R.id.seek_ashar);
        menit_ashar = (TextView) viewRoot.findViewById(R.id.correction_ashar);
        seek_ashar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                correct_ashar = String.valueOf(progress-10);
                if ((progress - 10) < 0){
                    menit_ashar.setText(String.valueOf(progress-10) + " Menit");
                } else {
                    menit_ashar.setText("+" + String.valueOf(progress-10) + " Menit");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seek_maghrib = (SeekBar) viewRoot.findViewById(R.id.seek_maghrib);
        menit_maghrib = (TextView) viewRoot.findViewById(R.id.correction_maghrib);
        seek_maghrib.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                correct_maghrib = String.valueOf(progress-10);
                if ((progress - 10) < 0){
                    menit_maghrib.setText(String.valueOf(progress-10) + " Menit");
                } else {
                    menit_maghrib.setText("+" + String.valueOf(progress-10) + " Menit");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seek_isya = (SeekBar) viewRoot.findViewById(R.id.seek_isya);
        menit_isya = (TextView) viewRoot.findViewById(R.id.correction_isya);
        seek_isya.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                correct_isya = String.valueOf(progress-10);
                if ((progress - 10) < 0){
                    menit_isya.setText(String.valueOf(progress-10) + " Menit");
                } else {
                    menit_isya.setText("+" + String.valueOf(progress-10) + " Menit");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        btn_send_correction = (Button) viewRoot.findViewById(R.id.btn_send_correction);
        btn_send_correction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  TODO Sending bluetooth command
                Toast.makeText(getContext(), "*9|" + correct_shubuh + "|" + correct_syuruq + "|" + correct_zhuhur + "|" + correct_ashar + "|" + correct_maghrib + "|" + correct_isya + "#", Toast.LENGTH_SHORT).show();
//                sendBluetoothMessage("*9|" + correct_shubuh + "|" + correct_syuruq + "|" + correct_zhuhur + "|" + correct_ashar + "|" + correct_maghrib + "|" + correct_isya + "#");
            }
        });

        return viewRoot;
    }

    private void sendBluetoothMessage(String message){
        if (mBluetooth.isConnected()) {
            mBluetooth.SendMessage(message);
        } else {
            Toast.makeText(getContext(), getString(R.string.msg_error_connect), Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
