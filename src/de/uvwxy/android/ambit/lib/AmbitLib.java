package de.uvwxy.android.ambit.lib;

import java.util.HashMap;
import java.util.Iterator;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import de.uvwxy.melogsta.Log;

public class AmbitLib {
    private static final String TAG = "AmbitLib";
    private static final String ACTION_USB_PERMISSION = "de.uvwxy.ambit.lib.USB_PERMISSION";
    private PendingIntent mPermissionIntent;
    private UsbManager mUsbManager;
    private Context mCtx;

    public AmbitLib(Context ctx) {
        this.mCtx = ctx;
        this.mUsbManager = (UsbManager) mCtx.getSystemService(Context.USB_SERVICE);

    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (device != null) {
                            //call method to set up device communication
                            Log.d(TAG, "permission granted for device " + device);
                        }
                    } else {
                        Log.d(TAG, "permission denied for device " + device);
                    }
                }
            }
        }
    };

    public UsbDevice findFirstAmbit2() {
        UsbDevice ret = null;
        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            if (device.getVendorId() == DeviceIds.AMBIT_2_VID //
                    && device.getProductId() == DeviceIds.AMBIT_2_PID) {
                ret = device;
            }
        }
        return ret;
    }

    public void requestPermission(UsbDevice device) {
        mPermissionIntent = PendingIntent.getBroadcast(mCtx, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        mCtx.registerReceiver(mUsbReceiver, filter);
        mUsbManager.requestPermission(device, mPermissionIntent);
    }

}
