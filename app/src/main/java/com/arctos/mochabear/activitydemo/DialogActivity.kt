package com.arctos.mochabear.activitydemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class DialogActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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