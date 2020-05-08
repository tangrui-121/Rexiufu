package com.text.rexiufu.rexiufu;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.text.rexiufu.rexiufu.FileUtils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RexiufuUtils {
    public static final String ConnectTimeout = "sun.net.client.defaultConnectTimeout";
    public static final String ReadTimeout = "sun.net.client.defaultReadTimeout";
    public static final String OUT_Time = "3000";
    public static final String BASE_URL = "http://192.168.1.161:9030/";
    public static final String GetUpdateUrl = "api_version?";

    public static void checkUpdate(final Activity context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL myURL = null;
                HttpURLConnection conn = null;
                String hotWeb = BASE_URL + GetUpdateUrl + "&package_name=" + context.getPackageName() + "&version=0";
                try {
                    myURL = new URL(hotWeb);
                    if (hotWeb.substring(0, 5).equalsIgnoreCase("https")) {
                        conn = (HttpsURLConnection) myURL.openConnection();
                    } else {
                        conn = (HttpURLConnection) myURL.openConnection();
                    }
                    conn.setInstanceFollowRedirects(true);
                    conn.setRequestMethod("GET");
                    System.setProperty(ConnectTimeout, OUT_Time);
                    System.setProperty(ReadTimeout, OUT_Time);
                    conn.connect();
                    if (conn.getResponseCode() == 200) {
                        String result = streamToString(conn.getInputStream());
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.optBoolean("result")) {
                            JSONObject updateData = jsonObject.optJSONObject("content");
                            if (updateData != null) {
                                Log.e("111", "response = " + updateData.toString());
                                final String downloadUrl = updateData.optString("file_path");
                                final int serverVersion = updateData.optInt("version");
                                final String md5 = updateData.optString("file_md5");
                                Log.e("111", "file_path = " + downloadUrl);
                                downLoad(downloadUrl, FileUtils.getFilename(downloadUrl), md5);
                            }
                        }
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    private static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            return null;
        }
    }

    public static void downLoad(final String path, final String FileName, final String md5) {
        try {
            URL url = new URL(path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(5000);
            con.setConnectTimeout(5000);
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {
                InputStream is = con.getInputStream();//获取输入流
                FileOutputStream fileOutputStream = null;//文件输出流
                if (is != null) {
                    FileUtils fileUtils = new FileUtils();
                    if (!fileUtils.createFile(FileName).exists()) {
                        fileOutputStream = new FileOutputStream(fileUtils.createFile(FileName));//指定文件保存路径，代码看下一步
                        byte[] buf = new byte[1024];
                        int ch;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);//将获取到的流写入文件中
                        }
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}