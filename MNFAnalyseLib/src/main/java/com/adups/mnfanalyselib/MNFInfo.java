package com.adups.mnfanalyselib;

import java.util.List;

/**
 * @author fighter_lee
 * @date 2018/1/19
 */
public class MNFInfo {

    private byte[] logical_node_address;
    private String version;
    private byte[] fcid;
    private byte[] drm;
    private byte[] infor_prom_trans_shift_lock_required;
    private byte[] partnumber_of_smd;
    private byte[] partnumber_of_lic;
    private byte[] max_programming_time;
    private byte[] number_of_software_parts;
    private byte[] mnf_version;
    private List<PNInfo> pn_info_list;

    public byte[] getLogical_node_address() {
        return logical_node_address;
    }

    public void setLogical_node_address(byte[] logical_node_address) {
        this.logical_node_address = logical_node_address;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public byte[] getFcid() {
        return fcid;
    }

    public void setFcid(byte[] fcid) {
        this.fcid = fcid;
    }

    public byte[] getDrm() {
        return drm;
    }

    public void setDrm(byte[] drm) {
        this.drm = drm;
    }

    public byte[] getInfor_prom_trans_shift_lock_required() {
        return infor_prom_trans_shift_lock_required;
    }

    public void setInfor_prom_trans_shift_lock_required(byte[] infor_prom_trans_shift_lock_required) {
        this.infor_prom_trans_shift_lock_required = infor_prom_trans_shift_lock_required;
    }

    public byte[] getPartnumber_of_smd() {
        return partnumber_of_smd;
    }

    public void setPartnumber_of_smd(byte[] partnumber_of_smd) {
        this.partnumber_of_smd = partnumber_of_smd;
    }

    public byte[] getPartnumber_of_lic() {
        return partnumber_of_lic;
    }

    public void setPartnumber_of_lic(byte[] partnumber_of_lic) {
        this.partnumber_of_lic = partnumber_of_lic;
    }

    public byte[] getMax_programming_time() {
        return max_programming_time;
    }

    public void setMax_programming_time(byte[] max_programming_time) {
        this.max_programming_time = max_programming_time;
    }

    public byte[] getNumber_of_software_parts() {
        return number_of_software_parts;
    }

    public void setNumber_of_software_parts(byte[] number_of_software_parts) {
        this.number_of_software_parts = number_of_software_parts;
    }

    public byte[] getMnf_version() {
        return mnf_version;
    }

    public void setMnf_version(byte[] mnf_version) {
        this.mnf_version = mnf_version;
    }

    public List<PNInfo> getPn_info_list() {
        return pn_info_list;
    }

    public void setPn_info_list(List<PNInfo> pn_info_list) {
        this.pn_info_list = pn_info_list;
    }
}
