package de.uvwxy.ambit.lib;


public enum AmbitType {
    
    AMBIT_2(0x1493, 0x0019);
    
    private short vid;
    private short pid;
    
    AmbitType(int vid, int pid){
        this.vid = (short) vid;
        this.pid = (short) pid;
    }
    
    public short pid(){
        return pid;
    }
    
    public short vid(){
        return vid;
    }
}
