package com.adups.mnfanalyselib;

import java.util.List;

/**
 * @author fighter_lee
 * @date 2018/1/19
 */
public class PNInfo {

    private String modid;
    private String part_number;
    private byte[] file_size_in_kB;
    private byte[] md_of_partnumber;
    private String moduleType;
    private String rpoCode;
    private List<String> fileNameList;

    public String getModid() {
        return modid;
    }

    public void setModid(String modid) {
        this.modid = modid;
    }

    public String getPart_number() {
        return part_number;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
    }

    public byte[] getFile_size_in_kB() {
        return file_size_in_kB;
    }

    public void setFile_size_in_kB(byte[] file_size_in_kB) {
        this.file_size_in_kB = file_size_in_kB;
    }

    public byte[] getMd_of_partnumber() {
        return md_of_partnumber;
    }

    public void setMd_of_partnumber(byte[] md_of_partnumber) {
        this.md_of_partnumber = md_of_partnumber;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getRpoCode() {
        return rpoCode;
    }

    public void setRpoCode(String rpoCode) {
        this.rpoCode = rpoCode;
    }

    public List<String> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }
}
