package de.uvwxy.android.ambit.fragments;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import de.uvwxy.android.ambit.R;
import de.uvwxy.android.ambit.lib.AmbitLib;
import de.uvwxy.android.ambit.lib.HIDConnection;

public class FragmentMain extends Fragment {
    public static final String TAG = "Android Ambit FragmentMain";
    private Context mCtx;

    private Button mBtnTest = null;
    private TextView tvInfo = null;
    private OnClickListener mStartTestListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            AmbitLib ambit = new AmbitLib(mCtx);

            UsbDevice d = ambit.findFirstAmbit2();
            if (d != null) {
                tvInfo.setText("Found Ambit 2 Watch!");
                tvInfo.setText("Requesting permission");
                ambit.requestPermission(d);
            } else {
                tvInfo.setText("Ambit 2 Watch not found!");

            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        mCtx = getActivity().getApplicationContext();

        mBtnTest = (Button) rootView.findViewById(R.id.btnTest);
        tvInfo = (TextView) rootView.findViewById(R.id.tvInfo);

        mBtnTest.setOnClickListener(mStartTestListener);
        super.onCreate(savedInstanceState);
        return rootView;

    }
}
