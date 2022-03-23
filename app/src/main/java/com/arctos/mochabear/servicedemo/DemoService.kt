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
}