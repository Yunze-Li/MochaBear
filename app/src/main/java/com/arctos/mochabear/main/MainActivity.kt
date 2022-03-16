package com.arctos.mochabear.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.arctos.mochabear.R
import com.arctos.mochabear.mapdemo.GoogleMapDemoActivity

class MainActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        val googleMapDemoButton = findViewById<AppCompatButton>(R.id.google_map_demo_button)

        googleMapDemoButton.setOnClickListener {
            val intent = Intent(this, GoogleMapDemoActivity::class.java)
            startActivity(intent)
        }

        Log.d(TAG, "onCreate is triggered!")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart is triggered!")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume is triggered!")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause is triggered!")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop is triggered!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy is triggered!")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart is triggered!")
    }

}