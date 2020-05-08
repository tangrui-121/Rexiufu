package com.text.rexiufu.handler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void a1() {
        Looper.prepare();
        new Handler().post(new Runnable() {
            @Override
            public void run() {

            }
        });
        Looper.loop();


        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).run();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

    }

    private class TextTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
