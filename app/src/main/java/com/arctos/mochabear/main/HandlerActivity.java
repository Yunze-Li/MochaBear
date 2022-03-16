package com.arctos.mochabear.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class HandlerActivity extends AppCompatActivity {
    private static final String TAG = "HandlerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testSendMessage();
    }

    public void testSendMessage() {
        Handler handler = new MyHandler(this);
        Message message = Message.obtain();
        message.obj = "test handler send message";
        handler.sendMessage(message);
    }

    //注1： 为什么要用静态内部？？？
    static class MyHandler extends Handler {
        WeakReference<AppCompatActivity> activityWeakReference; // 注2：为何要用弱引用？？？

        public MyHandler(AppCompatActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, (String) msg.obj);
        }
    }
}