package com.abupdate.androiddivconfiglibs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.util.Pair;

public class Utils {

    public static boolean modifyFileContent(String fileName, Pair<String, String>... pairs) {

        try {
            File file = new File(fileName);
            Long fileLength = file.length();
            byte[] fileContext = new byte[fileLength.intValue()];
            FileInputStream in = new FileInputStream(file);
            in.read(fileContext);
            in.close();
            String str = new String(fileContext);
            for (Pair<String, String> pair : pairs) {
                String oldstr = pair.getKey();
                String newStr = pair.getValue();
                str = str.replace(oldstr, newStr);

                PrintWriter out=new PrintWriter(fileName);
                out.write(str.toCharArray());
                out.flush();
                out.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean modifyFileContent(String fileName,String content) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content.toCharArray());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
