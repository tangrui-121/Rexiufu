package com.text.rexiufu.rexiufu;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileUtils {
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    private String path = Environment.getExternalStorageDirectory().toString() + "/008";

    public FileUtils() {
//        File externalStorageDirectory = Environment.getExternalStorageDirectory();
//        File fileDir = new File(externalStorageDirectory, "008");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public File createFile(String FileName) {
        return new File(path, FileName);
    }

    public static String getFilename(String file_url) {
        int i = file_url.lastIndexOf("/");
        return file_url.substring(i + 1);
    }

    //返回下载的文件的md5值，与接口返回的md5进行比较，一样的话再进行安装操作
    public static String fileMd5sum(String filePath) {
        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        try {
            fis = new FileInputStream(filePath);
            md5 = MessageDigest.getInstance("MD5");
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
            return toHexString(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }
}