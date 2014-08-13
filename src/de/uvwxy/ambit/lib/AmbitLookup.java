package de.uvwxy.ambit.lib;

public interface AmbitLookup {
    public AmbitDevice find(short vendorId, short productId);
}
