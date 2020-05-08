package com.text.rexiufu.shortcut;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.text.rexiufu.R;

public class ShortCutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortcut);
        setTitle("创建桌面快捷方式");
    }

    public void beforeO(View view) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        createShortcutBelowO(this, "O之前", bitmap);
    }

    public void afterO(View view) {
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        BitmapDrawable bd = (BitmapDrawable) drawable;
//        final Bitmap bmm = bd.getBitmap();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createShortcutAboveO(this, "O之后", bitmap);
        }
    }

    // 由该action可知，我们的创建快捷方式广播会由launcher，也就是系统桌面来接收
    public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    public void createShortcutBelowO(Context ctx, String name, Bitmap icon) {
        Intent shortcutIntent = new Intent(ACTION_INSTALL_SHORTCUT);
        // 快捷方式的名字
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        // 快捷方式的bitmap尽可能小，因为广播内容超过2MB会抛出异常
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, icon);
        // 设置是否允许重复创建快捷方式，该选项非必填，默认是允许
        shortcutIntent.putExtra("duplicate", false);

        // 快捷方式执行的intent，比如启动应用在AndroidManifest中配置的入口Activity
        Intent launchIntent = new Intent();
        try {
            launchIntent.setClass(ctx, Class.forName("com.text.rexiufu.shortcut.ShortCutActivity"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
        ctx.sendBroadcast(shortcutIntent);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createShortcutAboveO(Context ctx, String name, Bitmap icon) {
        ShortcutManager shortcutManager = (ShortcutManager) ctx.getSystemService(Context.SHORTCUT_SERVICE);
        /**
         * 判断是否支持该方式动态创建
         */
        if (shortcutManager.isRequestPinShortcutSupported()) {
            // 快捷方式执行的intent，比如启动应用在AndroidManifest中配置的入口Activity
            Intent launchIntent = new Intent();
            try {
                launchIntent.setClass(ctx, Class.forName("com.text.rexiufu.shortcut.MvvmMainActivity"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ShortcutInfo.Builder builder = new ShortcutInfo.Builder(ctx, "The only id")
                    .setShortLabel(name)
                    .setIcon(Icon.createWithBitmap(icon))
                    .setIntent(launchIntent);
            /**
             * 第二个参数为弹出创建快捷方式确认框时的回调PendingIntent，此例不关注该回调，因此为null，
             * 如果需要监听该回调，需要自定义一个BroadcastReceiver，可参考参考文献中的例子
             */
            shortcutManager.requestPinShortcut(builder.build(), null);
        }
    }

}