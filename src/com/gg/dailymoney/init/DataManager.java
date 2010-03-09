package com.gg.dailymoney.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class DataManager {

    public DataManager() {
        folder = ".\\bin\\";
    }

    private BufferedReader openReadFile(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream((new StringBuilder()).append(folder).append(filename).toString());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            return new BufferedReader(inputStreamReader);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private PrintWriter openWriteFile(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream((new StringBuilder()).append(folder).append(filename).toString(), true);
            return new PrintWriter(fileOutputStream);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void clearFileContent(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream((new StringBuilder()).append(folder).append(filename).toString(), false);
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void storeLicense(String license) {
        clearFileContent(LICSFILE);
        PrintWriter writer = openWriteFile(LICSFILE);
        writer.println(license);
        writer.close();
    }

    public boolean existLicense() {
        File file = new File((new StringBuilder()).append(folder).append(LICSFILE).toString());
        return file.exists();
    }

    public boolean isLicenseCorrect() {
        BufferedReader reader;
        reader = openReadFile(LICSFILE);
        if (reader != null) {
            String os_arch1 = System.getProperty("os.arch");
            String os_name1 = System.getProperty("os.name");
            String user_name1 = System.getProperty("user.name");
            String hw_id1 = Hardware.getHwId();
            String hd_id1 = Hardware.getHdId();
            try {
                String license = reader.readLine();
                reader.close();
                if (license != null) {
                    license = Criptography.decript(license, Criptography.licenseKey);
                    String user_name2 = license.split(";;")[0];
                    String hd_id2 = license.split(";;")[1];
                    String os_arch2 = license.split(";;")[2];
                    String os_name2 = license.split(";;")[3];
                    String hw_id2 = license.split(";;")[4];
                    return user_name1.equals(user_name2) && hd_id1.equals(hd_id2) && os_arch1.equals(os_arch2) && os_name1.equals(os_name2) && hw_id1.equals(hw_id2);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean isLicenseCorrect(String license) {
        String storableLicense = license;
        String os_arch1 = System.getProperty("os.arch");
        String os_name1 = System.getProperty("os.name");
        String user_name1 = System.getProperty("user.name");
        String hw_id1 = Hardware.getHwId();
        String hd_id1 = Hardware.getHdId();
        license = Criptography.decript(license, Criptography.licenseKey);
        if (license != null) {
            String user_name2 = license.split(";;")[0];
            String hd_id2 = license.split(";;")[1];
            String os_arch2 = license.split(";;")[2];
            String os_name2 = license.split(";;")[3];
            String hw_id2 = license.split(";;")[4];
            if (user_name1.equals(user_name2) && hd_id1.equals(hd_id2) && os_arch1.equals(os_arch2) && os_name1.equals(os_name2) && hw_id1.equals(hw_id2)) {
                storeLicense(storableLicense);
                return true;
            }
        }
        return false;
    }

    private String concat(String str1, String str2) {
        return (new StringBuilder()).append(str1).append(";;").append(str2).toString();
    }

    private String concat(String[] strs) {
        StringBuilder sbuilder = new StringBuilder();
        for(int i=0; i<strs.length-1; i++) {
            sbuilder.append(strs[i]);
            sbuilder.append(";;");
        }
        sbuilder.append(strs[strs.length-1]);
        return sbuilder.toString();
    }

    public String getSoftwareKey() {
        String os_arch = System.getProperty("os.arch");
        String os_name = System.getProperty("os.name");
        String user_name = System.getProperty("user.name");
        String hw_id = Hardware.getHwId();
        String hd_id = Hardware.getHdId();
        String key = concat(concat(concat(concat(user_name, hd_id), os_arch), os_name), hw_id);
        key = Criptography.encript(key, Criptography.activationKey);
        return key;
    }

    private String folder;
    private final String INITFILE = "parameters.ini";
    private final String LICSFILE = "license.ini";
    private final String CONFFILE = "configuration.ini";
}
