package com.arctos.mochabear.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.arctos.mochabear.R
import com.arctos.mochabear.customviewdemo.CustomViewDemoActivity

class MainActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate is triggered!")

        setContentView(R.layout.main_activity)

        // Google Map Demo
        val googleMapDemoButton = findViewById<AppCompatButton>(R.id.google_map_demo_button)
        googleMapDemoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }

        // Custom View Demo
        val customViewDemoButton = findViewById<AppCompatButton>(R.id.custom_view_demo_button)
        customViewDemoButton.setOnClickListener {
            val intent = Intent(this, CustomViewDemoActivity::class.java)
            val resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    Log.d(TAG, "data: $data received!")
                }
            }
            resultLauncher.launch(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_1 -> Toast.makeText(this, "You clicked Option 1", Toast.LENGTH_SHORT).show()
            R.id.option_2 -> Toast.makeText(this, "You clicked Option 2", Toast.LENGTH_SHORT).show()
        }
        return true
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