package com.abupdate.androiddivconfiglibs;

import java.io.*;

import brut.androlib.Androlib;
import brut.androlib.ApkDecoder;
import javafx.util.Pair;

/**
 * @author fighter_lee
 * @date 2018/3/2
 */
public class DivConfigImpl implements DivConfigInter {

    private ConfigBean configBean;
    private String outputAPKFileName;

    @Override
    public DivConfigInter setConfig(ConfigBean configBean) {
        this.configBean = configBean;
        return this;
    }

    public File dlFile() throws Exception {
        File totalFile = checkoutFromSVN();
        if (null != totalFile) {
            File targetAPK = generalAPK(totalFile);
            return compileFile(targetAPK);
        }
        return null;
    }

    private File generalAPK(File totalFile) {
        StringBuilder builder = new StringBuilder();
        if (configBean.getDevice().getType().equals(Device.CAR.getType())) {
            builder.append("Car");
        } else if (configBean.getDevice().getType().equals(Device.IOT.getType())) {
            builder.append("IOT");
        }
        if (configBean.isNeedIcon()) {
            builder.append("WithIcon");
        } else {
            builder.append("NoIcon");
        }
        outputAPKFileName = "/FOTA_" + builder.toString() + ".apk";
        File targetFile = null;
        for (File file : totalFile.listFiles()) {
            if (file.getName().contains(builder.toString())) {
                targetFile = file;
            }
        }
        return targetFile;
    }

    public File compileFile(File srcFile) throws Exception {
        System.out.println(Constants.APKTOOL_PATH + " d " + srcFile.getAbsolutePath() + " -o " + srcFile.getParentFile().getAbsolutePath() + Constants.APKTOOL_COMPILE_DIR);
        if (srcFile == null || srcFile.isDirectory()) {
            throw new IOException("编译APK异常：1001");
        }
        File compileDir = new File(configBean.getTempFilePath() + Constants.APKTOOL_COMPILE_DIR);
        ApkDecoder apkDecoder = new ApkDecoder();
        apkDecoder.setApkFile(srcFile);
        apkDecoder.setOutDir(compileDir);
        apkDecoder.decode();
        File outputFile = new File(configBean.getTempFilePath() + outputAPKFileName);
        File androidManifest = new File(compileDir, "AndroidManifest.xml");
        if (alterManifest(androidManifest)) {
            File customConfigFile = new File(compileDir, "assets/CustomConfig.properties");
            if (alterConfig(customConfigFile)) {
                new Androlib().build(compileDir, outputFile);
            }
        }
        if (outputFile.exists() && outputFile.length() > 0) {
            return outputFile;
        }
        return null;
    }

    @Override
    public File execute() throws Exception {
        return dlFile();
    }

    /**
     * 修改AndroidManifest文件
     *
     * @param androidManifest
     */
    private boolean alterManifest(File androidManifest) throws FileNotFoundException {
        Pair<String, String> idPair = new Pair<>(Constants.OLD_PRODUCT_ID, configBean.getProductID());
        Pair<String, String> secretPair = new Pair<>(Constants.OLD_PRODUCT_SECRET, configBean.getProductSecret());
        if (Utils.modifyFileContent(androidManifest.getAbsolutePath(), idPair, secretPair)) {
            return true;
        }
        return false;
    }

    /**
     * 修改资源目录下自定义配置文件
     *
     * @param configFile
     * @return
     */
    private boolean alterConfig(File configFile) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("{")
                .append("\"").append(Constants.RO_FOTA_MIDTYPE).append("\"").append(":").append("\"").append(configBean.getMidType().getType()).append("\",")
                .append("\"").append(Constants.RO_FOTA_PUSH).append("\"").append(":").append(configBean.isRequestPush()).append(",")
                .append("\"").append(Constants.RO_FOTA_LOCALUPDATE).append("\"").append(":").append(configBean.isLocalUpdate()).append(",")
                .append("\"").append(Constants.RO_FOTA_ORIENTATION).append("\"").append(":").append("\"").append(configBean.getScreenOrientation().getType()).append("\",")
                .append("\"").append(Constants.RO_FOTA_OEM).append("\"").append(":").append("\"").append(configBean.getOem()).append("\",")
                .append("\"").append(Constants.RO_FOTA_MODELS).append("\"").append(":").append("\"").append(configBean.getModels()).append("\",")
                .append("\"").append(Constants.RO_FOTA_PLATFORM).append("\"").append(":").append("\"").append(configBean.getPlatform()).append("\",")
                .append("\"").append(Constants.RO_FOTA_TYPE).append("\"").append(":").append("\"").append(configBean.getDeviceType()).append("\"")
                .append("}");
        return Utils.modifyFileContent(configFile.getAbsolutePath(),builder.toString());
    }

    public File checkoutFromSVN() {
        Runtime run = Runtime.getRuntime();
        Process proc = null;
        try {
            //            proc = run.exec("/bin/bash", null, wd);
            proc = run.exec("sh");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (proc != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            out.println("cd " + configBean.getSvnPath());
            out.println("pwd");
            out.println("svn checkout svn://svnhome.adfuture.cn:8888/fota/iot/androidsdk/maven/releaseAPK");
            out.println("exit");
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                proc.waitFor();
                in.close();
                out.close();
                proc.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File releaseApkPath = new File(configBean.getSvnPath() + Constants.path_releaseAPK);
        if (releaseApkPath.exists()) {
            return releaseApkPath;
        }
        return null;
    }

}
