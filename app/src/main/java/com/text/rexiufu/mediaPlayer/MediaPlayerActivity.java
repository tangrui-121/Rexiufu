package com.text.rexiufu.mediaPlayer;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.text.rexiufu.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MediaPlayerActivity extends Activity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    SurfaceHolder surfaceHolder;

    SurfaceView surfaceView;
    RelativeLayout view_control;
    LinearLayout lin_control;
    ImageView img_start, img_allscreen;
    ProgressBar pro_schedule;
    TextView text_schedule;
    FrameLayout fl_group;
    RelativeLayout rel_touch;

    Boolean isPrepared = false;//准备好？
    Boolean isplaying = false;//播放中？
    Boolean isallscreen = false;//全屏中？

    private String videoUrl = "http://183.59.160.61:30001/PLTV/88888905/224/3221227505/index.m3u8";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);

        surfaceView = findViewById(R.id.surfaceView);
        view_control = findViewById(R.id.view_control);
        lin_control = findViewById(R.id.lin_control);
        img_start = findViewById(R.id.img_start);
        img_allscreen = findViewById(R.id.img_allscreen);
        pro_schedule = findViewById(R.id.pro_schedule);
        text_schedule = findViewById(R.id.text_schedule);
        fl_group = findViewById(R.id.fl_group);

        getTouchView();

//        view_control.setOnClickListener(this);
//        img_start.setOnClickListener(this);
//        img_allscreen.setOnClickListener(this);

        String path = Environment.getExternalStorageDirectory().toString() + "/008/flower.mp4";
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(new MyCallBack());
            mediaPlayer.setScreenOnWhilePlaying(true);
            mediaPlayer.setLooping(true);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    isPrepared = true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    lin_control.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_control:
                if (lin_control.getVisibility() == View.VISIBLE) {
                    lin_control.setVisibility(View.GONE);
                    mHandler.removeMessages(1);
                } else {
                    lin_control.setVisibility(View.VISIBLE);
                    Message msg = mHandler.obtainMessage(1);
                    mHandler.sendMessageDelayed(msg, 5000);
                }
                break;
            case R.id.img_start:
                if (isplaying) {
                    isplaying = false;
                    img_start.setImageResource(R.mipmap.icon_start);
                    mediaPlayer.pause();
                } else {
                    if (isPrepared) {
                        isplaying = true;
                        img_start.setImageResource(R.mipmap.icon_stop);
                        mediaPlayer.start();
                        listenerStatus();//定时器的暂停 恢复还存在问题
                    }
                }
                break;
            case R.id.img_allscreen:
                if (isallscreen) {
                    isallscreen = false;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            dp2px(MediaPlayerActivity.this, 200));
                    fl_group.setLayoutParams(lp);
                } else {
                    isallscreen = true;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT);
                    fl_group.setLayoutParams(lp);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (null != timer)
            timer.cancel();
    }

    Timer timer;

    private void listenerStatus() {
        if (null != timer)
            timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pro_schedule.setProgress((int) (((double) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
                        text_schedule.setText(Utils.timeParse(mediaPlayer.getCurrentPosition()) + "/" + Utils.timeParse(mediaPlayer.getDuration()));
                    }
                });
            }
        }, 1000, 1000);
    }

    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mediaPlayer.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isallscreen) {//全屏切换半屏
                    isallscreen = false;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 手动横屏
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            dp2px(MediaPlayerActivity.this, 200));
                    fl_group.setLayoutParams(lp);
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    //动态设置视频的宽高
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initVideoView(String path) {

    }

    String TAG = "滑动监听";
    float posX, posY, curPosX, curPosY;

    //获取整个布局
//    private void getTouchView() {
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(R.layout.main, null);
//        setOnLayoutTouchListener();
//    }


    private void getTouchView() {
        rel_touch = findViewById(R.id.rel_touch);
        setOnViewTouchListener(rel_touch);
    }

    private void setOnViewTouchListener(final View view) {


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Boolean is = false;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        posX = event.getX();
                        posY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curPosX = event.getX();
                        curPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if ((curPosX - posX > 0) && (Math.abs(curPosX - posX) > 250) && (Math.abs(curPosX - posX) > Math.abs(curPosY - posY))) {
                            Log.e(TAG, "向右滑动了" + Math.abs(curPosX - posX));
                            is = true;
                        } else if ((curPosX - posX < 0) && (Math.abs(curPosX - posX) > 250) && (Math.abs(curPosX - posX) > Math.abs(curPosY - posY))) {
                            Log.e(TAG, "向左滑动了" + Math.abs(curPosX - posX));
                            is = true;
                        } else if ((curPosY - posY > 0) && (Math.abs(curPosY - posY) > 250) && (Math.abs(curPosX - posX) < Math.abs(curPosY - posY))) {
                            if (posX <= view.getWidth() / 2)
                                Log.e(TAG, "在左边向下滑动了" + Math.abs(curPosY - posY));
                            else
                                Log.e(TAG, "在右边向下滑动了" + Math.abs(curPosY - posY));
                            is = true;
                        } else if ((curPosY - posY < 0) && (Math.abs(curPosY - posY) > 250) && (Math.abs(curPosX - posX) < Math.abs(curPosY - posY))) {
                            if (posX <= view.getWidth() / 2)
                                Log.e(TAG, "在左边向上滑动了" + Math.abs(curPosY - posY));
                            else
                                Log.e(TAG, "在右边向上滑动了" + Math.abs(curPosY - posY));
                            is = true;
                        } else {
                            Log.e(TAG, "只是点击");
                            is = false;
                        }
                }
                Log.e(TAG, "跳出判断");
                return is;
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
}