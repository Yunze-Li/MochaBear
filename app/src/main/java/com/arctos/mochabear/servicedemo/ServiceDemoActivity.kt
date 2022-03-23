package com.arctos.mochabear.servicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.arctos.mochabear.R

class ServiceDemoActivity : AppCompatActivity() {

    private lateinit var mBinder: DemoService.DemoBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinder = service as DemoService.DemoBinder
            mBinder.startService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBinder.stopService()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.service_demo)

        // start DemoService
        val startServiceButton = findViewById<AppCompatButton>(R.id.start_service_button)
        startServiceButton.setOnClickListener {
            val intent = Intent(this, DemoService::class.java)
            startService(intent)
        }

        // stop DemoService
        val stopServiceButton = findViewById<AppCompatButton>(R.id.stop_service_button)
        stopServiceButton.setOnClickListener {
            val intent = Intent(this, DemoService::class.java)
            stopService(intent)
        }

        // bind DemoService
        val bindServiceButton = findViewById<AppCompatButton>(R.id.bind_service_button)
        bindServiceButton.setOnClickListener {
            val intent = Intent(this, DemoService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        // unbind DemoService
        val unbindServiceButton = findViewById<AppCompatButton>(R.id.unbind_service_button)
        unbindServiceButton.setOnClickListener {
            unbindService(connection)
        }
    }
}