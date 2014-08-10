package de.uvwxy.android.ambit.lib;

import java.util.HashMap;
import java.util.Iterator;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;

public class HIDConnection {
    public static final String TAG = "Ambit HIDConnection";

    /**
     * http://stackoverflow.com/questions/13280581/using-android-to-communicate-
     * with-a-usb-hid-device
     * 
     * @param ctx
     * @return
     */
    public String getHidInfo(Context ctx) {
        StringBuilder ret = new StringBuilder();

        UsbManager mManager = (UsbManager) ctx.getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = mManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            ret.append("\n").append("Model: " + device.getDeviceName());
            ret.append("\n").append("ID: " + device.getDeviceId());
            ret.append("\n").append("Class: " + device.getDeviceClass());
            ret.append("\n").append("Protocol: " + device.getDeviceProtocol());
            ret.append("\n").append("Vendor ID " + device.getVendorId());
            ret.append("\n").append("Product ID: " + device.getProductId());
            ret.append("\n").append("Interface count: " + device.getInterfaceCount());
            ret.append("\n").append("---------------------------------------");
            // Get interface details
            for (int index = 0; index < device.getInterfaceCount(); index++) {
                UsbInterface mUsbInterface = device.getInterface(index);
                ret.append("\n").append("  *****     *****");
                ret.append("\n").append("  Interface index: " + index);
                ret.append("\n").append("  Interface ID: " + mUsbInterface.getId());
                ret.append("\n").append("  Inteface class: " + mUsbInterface.getInterfaceClass());
                ret.append("\n").append("  Interface protocol: " + mUsbInterface.getInterfaceProtocol());
                ret.append("\n").append("  Endpoint count: " + mUsbInterface.getEndpointCount());
                // Get endpoint details 
                for (int epi = 0; epi < mUsbInterface.getEndpointCount(); epi++) {
                    UsbEndpoint mEndpoint = mUsbInterface.getEndpoint(epi);
                    ret.append("\n").append("    ++++   ++++   ++++");
                    ret.append("\n").append("    Endpoint index: " + epi);
                    ret.append("\n").append("    Attributes: " + mEndpoint.getAttributes());
                    ret.append("\n").append("    Direction: " + mEndpoint.getDirection());
                    ret.append("\n").append("    Number: " + mEndpoint.getEndpointNumber());
                    ret.append("\n").append("    Interval: " + mEndpoint.getInterval());
                    ret.append("\n").append("    Packet size: " + mEndpoint.getMaxPacketSize());
                    ret.append("\n").append("    Type: " + mEndpoint.getType());
                }
            }
        }
        ret.append("\n").append(" No more devices connected.");
        return ret.toString();
    }

}
