package de.uvwxy.android.ambit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import de.uvwxy.ambit.lib.AmbitDevice;
import de.uvwxy.ambit.lib.AmbitType;
import de.uvwxy.android.ambit.R;
import de.uvwxy.android.ambit.lib.AmbitAndroid;
import de.uvwxy.android.ambit.lib.AmbitAndroid.AmbitAndroidPermission;
import de.uvwxy.melogsta.Log;

public class FragmentMain extends Fragment {
    public static final String TAG = "Android Ambit FragmentMain";
    private Context mCtx;

    private Button mBtnTest = null;
    private TextView tvInfo = null;

    private AmbitAndroidPermission aap = new AmbitAndroidPermission() {

        @Override
        public void granted(AmbitDevice d) {
            Log.d(TAG, "got device " + d);
            boolean success = d.open();
            Log.d(TAG, "device opende: " + success);
        }

        @Override
        public void denied() {

        }
    };

    private OnClickListener mStartTestListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            AmbitAndroid ambit = new AmbitAndroid(mCtx, aap);
            ambit.find(AmbitType.AMBIT_2.vid(), AmbitType.AMBIT_2.pid());

            tvInfo.setText("Scanning for device");
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
