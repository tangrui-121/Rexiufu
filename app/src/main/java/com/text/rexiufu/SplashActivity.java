package com.text.rexiufu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.text.rexiufu.appinfo.AppInfo;
import com.text.rexiufu.rexiufu.FixDexUtil;
import com.text.rexiufu.rexiufu.RexiufuUtils;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private TextView textview1;
    private List<AppInfo> appInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textview1 = findViewById(R.id.textview1);
        RexiufuUtils.checkUpdate(this);
        init();
        checkPermission();
        getAppInfos();
    }

    private void init() {
//        File externalStorageDirectory = Environment.getExternalStorageDirectory();
//        File fileDir = externalStorageDirectory != null ?
//                new File(externalStorageDirectory, "008") :
//                new File(getFilesDir(), FixDexUtil.DEX_DIR);// data/user/0/包名/files/odex（这个可以任意位置）
//        if (!fileDir.exists()) {
//            fileDir.mkdir();
//        }
        if (FixDexUtil.isGoingToFix(this)) {
            FixDexUtil.loadFixedDex(this, Environment.getExternalStorageDirectory());
            textview1.setText("正在修复。。。。");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10001);
        }
    }

    public void getAppInfos() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//        Date date = new Date(System.currentTimeMillis());
        new Thread(new Runnable() {
            @Override
            public void run() {
                PackageManager pm = getApplication().getPackageManager();
                List<PackageInfo> packgeInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
                appInfos = new ArrayList<AppInfo>();
                for (PackageInfo packgeInfo : packgeInfos) {
                    String appName = packgeInfo.applicationInfo.loadLabel(pm).toString();
                    String packageName = packgeInfo.packageName;
                    Drawable drawable = packgeInfo.applicationInfo.loadIcon(pm);
                    AppInfo appInfo = new AppInfo(appName, packageName, drawable);
                    appInfos.add(appInfo);
                }
            }
        }).start();
    }
}