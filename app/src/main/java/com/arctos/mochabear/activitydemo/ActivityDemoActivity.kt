package com.arctos.mochabear.activitydemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.arctos.mochabear.R

class ActivityDemoActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        // launch Normal Activity
        val normalActivityButton = findViewById<AppCompatButton>(R.id.normal_activity_button)
        normalActivityButton.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }

        // launch Dialog Activity
        val dialogActivityButton = findViewById<AppCompatButton>(R.id.dialog_activity_button)
        dialogActivityButton.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
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