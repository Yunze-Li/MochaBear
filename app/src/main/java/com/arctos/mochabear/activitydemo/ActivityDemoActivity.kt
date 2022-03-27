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

        // launch Dialog Activity
        val leakCanaryActivityButton =
            findViewById<AppCompatButton>(R.id.leak_canary_activity_button)
        leakCanaryActivityButton.setOnClickListener {
            val intent = Intent(this, LeakingActivity::class.java)
            startActivity(intent)
        }

        Log.d(TAG, "$TAG onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$TAG onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$TAG onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$TAG onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "$TAG onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "$TAG onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "$TAG onRestart())")
    }
}