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
import de.uvwxy.ambit.lib.AmbitDevice;
import de.uvwxy.ambit.lib.AmbitLookup;

public class AmbitAndroid implements AmbitLookup {
    private static final String TAG = "AmbitLib";
    private static final String ACTION_USB_PERMISSION = "de.uvwxy.ambit.lib.USB_PERMISSION";
    private PendingIntent mPermissionIntent;
    private UsbManager mUsbManager;
    private Context mCtx;
    private AmbitAndroidPermission aap;

    public interface AmbitAndroidPermission {
        public void granted(AmbitDevice d);

        public void denied();
    }

    public AmbitAndroid(Context ctx, AmbitAndroidPermission aap) {
        this.mCtx = ctx;
        this.aap = aap;
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
                            aap.granted(new AndroidAmbitDevice(mUsbManager, device));
                        }
                    } else {
                        aap.denied();
                    }
                }
            }
        }
    };

    public void requestPermission(UsbDevice device) {
        mPermissionIntent = PendingIntent.getBroadcast(mCtx, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        mCtx.registerReceiver(mUsbReceiver, filter);
        mUsbManager.requestPermission(device, mPermissionIntent);
    }

    @Override
    public AmbitDevice find(short vendorId, short productId) {
        UsbDevice ret = null;
        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            if (device.getVendorId() == vendorId //
                    && device.getProductId() == productId) {
                ret = device;
            }
        }
        requestPermission(ret);
        return null;
    }

}
