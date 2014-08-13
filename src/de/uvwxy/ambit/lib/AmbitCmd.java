package de.uvwxy.ambit.lib;


public enum AmbitCmd {
    
        ambit_command_device_info       (0x0000),
        ambit_command_time              (0x0300),
        ambit_command_date              (0x0302),
        ambit_command_status            (0x0306),
        ambit_command_personal_settings (0x0b00),
        ambit_command_unknown1          (0x0b04),
        ambit_command_log_count         (0x0b06),
        ambit_command_log_head_first    (0x0b07),
        ambit_command_log_head_peek     (0x0b08),
        ambit_command_log_head_step     (0x0b0a),
        ambit_command_log_head          (0x0b0b),
        ambit_command_gps_orbit_head    (0x0b15),
        ambit_command_data_write        (0x0b16),
        ambit_command_log_read          (0x0b17),
        ambit_command_data_tail_len     (0x0b18),
        ambit_command_lock_check        (0x0b19),
        ambit_command_lock_set          (0x0b1a),
        ambit_command_write_start       (0x0b1b); // Really!? Just a guess...
        
        private int cmd;
        private AmbitCmd(int cmd) {
            this.cmd = cmd;
        }
        
        public int getCmd(){
            return cmd;
        }
}
