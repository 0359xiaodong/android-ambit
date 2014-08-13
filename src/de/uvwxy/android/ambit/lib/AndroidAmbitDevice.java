package de.uvwxy.android.ambit.lib;

import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import de.uvwxy.melogsta.Log;

public class AndroidAmbitDevice extends de.uvwxy.ambit.lib.AmbitDevice {
    private static final String TAG = "ANDROID AMBIT DEVICE";
    UsbDevice device;
    UsbInterface iface;
    UsbEndpoint in;
    UsbEndpoint out;
    UsbDeviceConnection con;
    UsbManager manager;

    public AndroidAmbitDevice(UsbManager m, UsbDevice device) {
        this.device = device;
        this.manager = m;
    }

    @Override
    public boolean open() {
        iface = device.getInterface(0);
        Log.d(TAG, "Endpoint Count: " + iface.getEndpointCount());
        UsbEndpoint ep0 = iface.getEndpoint(0);
        UsbEndpoint ep1 = iface.getEndpoint(1);

        if (ep0.getDirection() == UsbConstants.USB_DIR_IN) {
            in = ep0;
            out = ep1;
        } else {
            in = ep1;
            out = ep0;
        }

        con = manager.openDevice(device);

        return con != null;
    }

    @Override
    public boolean close() {
        boolean ret;
        try {
            con.close();
            ret = true;
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }

    @Override
    public void writeBytes(byte[] b) {
        //        con.
    }

}
