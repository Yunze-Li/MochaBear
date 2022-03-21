package com.arctos.mochabear.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT)
                .show()
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