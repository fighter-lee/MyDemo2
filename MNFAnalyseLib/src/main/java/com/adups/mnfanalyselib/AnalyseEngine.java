package com.adups.mnfanalyselib;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fighter_lee
 * @date 2018/1/19
 */
public class AnalyseEngine {

    public static final String UTF = "utf-8";

    public MNFInfo doAnalyse(String path) throws IOException, MnfParseException {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            System.out.println("path is invalid");
            throw new IOException("path is invalid");
        }

        FileInputStream fis = new FileInputStream(file);
        return doAnalyse(fis);
    }

    public MNFInfo doAnalyse(InputStream is) throws IOException, MnfParseException {
        byte[] buffer = new byte[1];
        List<Byte> record = new LinkedList<>();
        List<List<Byte>> storage = new ArrayList<>();
        int len;
        while ((len = is.read(buffer)) != -1) {
            if (buffer[0] == 0x1F) {
                List<Byte> bytes = new LinkedList<>();
                bytes.addAll(record);
                storage.add(bytes);
                record.clear();
            } else {
                record.add(buffer[0]);
            }
        }

        MNFInfo mnfInfo = new MNFInfo();
        mnfInfo.setPn_info_list(new ArrayList<PNInfo>());
        for (int i = 0; i < storage.size(); i++) {
            List<Byte> bytes = storage.get(i);
            if (i == 0) {
                //移除首位占位符
                bytes.remove(0);
            }

            byte[] b = listTobyte(bytes);
            setMNFInfo(mnfInfo, i, b);
        }
        return mnfInfo;
    }

    private void setMNFInfo(MNFInfo mnfInfo, int i, byte[] b) throws MnfParseException {
        switch (i) {
            case 0:
                if (b.length != 2) {
                    throw throwException("Logical_node_address is invalid");
                }
                mnfInfo.setLogical_node_address(b);
                break;
            case 1:
                if (b.length != 20) {
                    throw throwException("Version is invalid");
                }
                String version = "";
                try {
                    version = new String(b, UTF);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                mnfInfo.setVersion(version);
                break;
            case 2:
                if (b.length != 2) {
                    throw throwException("Fcid is invalid");
                }
                mnfInfo.setFcid(b);
                break;
            case 3:
                if (b.length != 1) {
                    throw throwException("Drm is invalid");
                }
                mnfInfo.setDrm(b);
                break;
            case 4:
                if (b.length != 1) {
                    throw throwException("Infor_prom_trans_shift_lock_required is invalid");
                }
                mnfInfo.setInfor_prom_trans_shift_lock_required(b);
                break;
            case 5:
                if (b.length != 19) {
                    throw throwException("Partnumber_of_smd is invalid");
                }
                mnfInfo.setPartnumber_of_smd(b);
                break;
            case 6:
                if (b.length != 19) {
                    throw throwException("Partnumber_of_lic is invalid");
                }
                mnfInfo.setPartnumber_of_lic(b);
                break;
            case 7:
                if (b.length != 4) {
                    throw throwException("Max_programming_time is invalid");
                }
                mnfInfo.setMax_programming_time(b);
                break;
            case 8:
                if (b.length != 1) {
                    throw throwException("Number_of_software_parts is invalid");
                }
                mnfInfo.setNumber_of_software_parts(b);
                break;
            case 9:
                if (b.length != 1) {
                    throw throwException("Mnf_version is invalid");
                }
                mnfInfo.setMnf_version(b);
                break;
            default:
                if (b.length == 56) {
                    //pn info
                    PNInfo pnInfo = new PNInfo();
                    setPninfo(pnInfo, b);
                    mnfInfo.getPn_info_list().add(pnInfo);
                }
        }
    }

    private void setPninfo(PNInfo pninfo, byte[] b) {
        byte[] modid_byte = new byte[4];
        byte[] part_number_byte = new byte[16];
        byte[] file_size_in_kB_byte = new byte[4];
        byte[] md_of_partnumber_byte = new byte[32];
        for (int i = 0; i < b.length; i++) {
            if (i < 4) {
                modid_byte[i] = b[i];
            }
            if (i >= 4 && i < 20) {
                part_number_byte[i - 4] = b[i];
            }

            if (i >= 20 && i < 24) {
                file_size_in_kB_byte[i - 20] = b[i];
            }

            if (i >= 24) {
                md_of_partnumber_byte[i - 24] = b[i];
            }
        }
        String modid = "";
        String part_number = "";
        try {
            int modid_int = byteArrayToInt(modid_byte);
            modid = (modid_int < 10 ? "0" : "") + modid_int;
            part_number = new String(part_number_byte, UTF);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throwException("编码转换异常");
        }

        pninfo.setPart_number(part_number);
        pninfo.setModid(modid);
        pninfo.setFile_size_in_kB(file_size_in_kB_byte);
        pninfo.setMd_of_partnumber(md_of_partnumber_byte);
    }

    private MnfParseException throwException(String error) {
        return new MnfParseException(error);
    }

    private byte[] listTobyte(List<Byte> list) {
        if (list == null || list.size() < 0)
            return null;
        byte[] bytes = new byte[list.size()];
        int i = 0;
        Iterator<Byte> iterator = list.iterator();
        while (iterator.hasNext()) {
            bytes[i] = iterator.next();
            i++;
        }
        return bytes;
    }

    //将高字节在前转低字节在后的byte数组转int
    public int byteArrayToInt(byte[] bArr) {
        if (bArr.length != 4) {
            return -1;
        }
        return (int) ((((bArr[0] & 0xff) << 24)
                | ((bArr[1] & 0xff) << 16)
                | ((bArr[2] & 0xff) << 8)
                | ((bArr[3] & 0xff) << 0)));
    }

}
