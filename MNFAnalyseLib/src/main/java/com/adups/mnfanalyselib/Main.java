package com.adups.mnfanalyselib;

import java.io.IOException;

/**
 * @author fighter_lee
 * @date 2018/2/24
 */
public class Main {

    public static void main(String[] args) {
        try {
            MNFInfo mnfInfo = new AnalyseEngine().doAnalyse("D:/document/ICI1 Ver3.mnf");
            System.out.println(mnfInfo.getDrm());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MnfParseException e) {
            e.printStackTrace();
        }
    }

}
