package de.uvwxy.ambit.lib;

public abstract class AmbitDevice {

    public AmbitDevice() {
    }

    public abstract boolean open();

    public abstract boolean close();

    public abstract void writeBytes(byte[] b);

    public String getSerialNumber() {
        String ret = null;

        return ret;
    }
}
