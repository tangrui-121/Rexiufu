package com.text.rexiufu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.text.rexiufu.aop.SingleClick;
import com.text.rexiufu.behavior.BehaviorActivity;
import com.text.rexiufu.behavior.BehaviorActivity1;
import com.text.rexiufu.dynamicView.TextviewActivity;
import com.text.rexiufu.fanshe.Constructors;
import com.text.rexiufu.mediaPlayer.MediaPlayerActivity;
import com.text.rexiufu.mvvm.MvvmMainActivity;
import com.text.rexiufu.objectbox.ObjectboxActivity;
import com.text.rexiufu.recyclerview.RecyclerViewActivity;
import com.text.rexiufu.rexiufu.BugClass;
import com.text.rexiufu.shortcut.ShortCutActivity;
import com.text.rexiufu.utils.NetCheckUtil;
import com.text.rexiufu.view.ChangePicActivity;
import com.text.rexiufu.view.PointActivity;
import com.text.rexiufu.viewpager.ViewpagerActivity;
import com.text.rexiufu.weight.PasswordInputView;
import com.text.rexiufu.xiaofeiji.MyMobiActivity;
import com.text.rexiufu.xiaofeiji.RoundActivity;
import com.text.rexiufu.xiaofeiji.RoundView;
import com.text.rexiufu.xiaofeiji.XiaoFeiJiActivity;
import com.text.rexiufu.zhiwen.FingerDemoActivity;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button test_aop;
    private TextView textview1, textview2;
    private PasswordInputView passwordinput;
    public final static String HTML_TEXT =
            "<p><font size=\"3\" color=\"red\">设置了字号和颜色</font></p>" +
                    "<b><font size=\"5\" color=\"blue\">设置字体加粗 蓝色 5号</font></font></b></br>" +
                    "<h1>这个是H1标签</h1></br>" +
                    "<p>这里显示图片：</p><img src=\"http://img4.imgtn.bdimg.com/it/u=390730400,1755278816&fm=21&gp=0.jpg\"";

    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("ping -c 3 www.baidu.com");
            int ret = p.waitFor();
            Log.i("222", "Process:"+ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        passwordinput = findViewById(R.id.passwordinput);
        textview1.setText("click me \n  dddd");
        textview2.setText(String.format(getResources().getString(R.string.version_add), "1.0", "2.2"));
//        textview2.setText(Html.fromHtml(HTML_TEXT));//这么写图片出不来
        HtmlShowPic();
        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BugClass(MainActivity.this);
            }
        });

        String[] names = {"喵", "汪", "咩"};
        List<String> nameList = Arrays.asList(names);

        //旧的循环方式
//        for (String name : nameList) {
//            System.out.print(name + "; ");
//        }

        //使用 lambda 表达式
        nameList.forEach((item) -> System.out.print(item + " "));

        passwordinput.setInputListener(new PasswordInputView.InputListener() {
            @Override
            public void onInputCompleted(String text) {
                Toast.makeText(MainActivity.this, "输入完成", Toast.LENGTH_SHORT).show();
            }
        });

        eeee();

        try {
            Constructors.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        aop();
    }

    private void aop() {
        boolean flag = false;
        test_aop = findViewById(R.id.test_aop);
        test_aop.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1500)// 添加点击注释
            @Override
            public void onClick(View v) {
                if (flag) {
                    System.out.println("throw an exception................");
                    throw new RuntimeException();
                }
                System.out.println("执行onClick................");
            }
        });
    }

    public void RecyclerViewClick(View view) {
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
    }

    public void MediaPlayerClick(View view) {
        startActivity(new Intent(MainActivity.this, MediaPlayerActivity.class));
    }

    public void ObjectBox(View view) {
        startActivity(new Intent(MainActivity.this, ObjectboxActivity.class));
    }

    public void DataBinding(View view) {
        startActivity(new Intent(MainActivity.this, MvvmMainActivity.class));
    }

    public void ShortCut(View view) {
        startActivity(new Intent(MainActivity.this, ShortCutActivity.class));
    }

    public void Point(View view) {
        startActivity(new Intent(MainActivity.this, PointActivity.class));
    }

    public void ChangePic(View view) {
        startActivity(new Intent(MainActivity.this, ChangePicActivity.class));
    }

    public void Behavior(View view) {
        startActivity(new Intent(MainActivity.this, BehaviorActivity.class));
    }

    public void Behavior1(View view) {
        startActivity(new Intent(MainActivity.this, BehaviorActivity1.class));
    }

    public void TextviewActivity(View view) {
        startActivity(new Intent(MainActivity.this, TextviewActivity.class));
    }

    public void zhiwen(View view) {
        startActivity(new Intent(MainActivity.this, FingerDemoActivity.class));
    }

    public void viewpager(View view) {
        startActivity(new Intent(MainActivity.this, ViewpagerActivity.class));
    }

    public void xiaofeiji(View view) {
        startActivity(new Intent(MainActivity.this, MyMobiActivity.class));
    }

    public void roundView(View view) {
        startActivity(new Intent(MainActivity.this, RoundActivity.class));
    }

    public void shurukuang(View view) {
        startActivity(new Intent(MainActivity.this, edittext.class));
    }

    private LevelListDrawable mDrawable = new LevelListDrawable();

    // 注意啦，这么写Handler是会造成内存泄漏的，实际项目中不要这么直接用。
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1123) {
//                Bitmap bitmap = (Bitmap) msg.obj;
//                BitmapDrawable drawable = new BitmapDrawable(null, bitmap);
//                mDrawable.addLevel(1, 1, drawable);
//                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
//                mDrawable.setLevel(1);
//
//                CharSequence charSequence = textview2.getText();
//                textview2.setText(charSequence);
//                textview2.invalidate();
            }
        }
    };

    private void HtmlShowPic() {
        textview2.setText(Html.fromHtml(HTML_TEXT, Html.FROM_HTML_MODE_COMPACT, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(final String source) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mDrawable.addLevel(0, 0, getResources().getDrawable(R.mipmap.ic_launcher));
                        mDrawable.setBounds(0, 0, 200, 200);

                        Bitmap bitmap;
                        try {
                            bitmap = BitmapFactory.decodeStream(new URL(source).openStream());
                            Message msg = handler.obtainMessage();
                            msg.what = 1123;
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                return mDrawable;
            }
        }, null));
    }


    private void eeee() {
        String aaa = "ssss";
        String bbb = "ssss";
        Log.e("111", "equals = " + aaa.equals(bbb));
        Log.e("111", "aaa hashcode = " + aaa.hashCode());
        Log.e("111", "bbb hashcode = " + bbb.hashCode());

        Float.floatToRawIntBits(0.09f);
    }
}