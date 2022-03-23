package com.arctos.mochabear.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class DemoService : Service() {

    private val mBinder = DemoBinder()

    class DemoBinder : Binder() {
        fun startService() {
            Log.d("DemoService", "DemoService started!")
        }

        fun stopService() {
            Log.d("DemoService", "DemoService stopped!")
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("DemoService", "DemoService onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("DemoService", "DemoService onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DemoService", "DemoService onDestroy()")
    }
}