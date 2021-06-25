package com.arctos.mochabear.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.arctos.mochabear.R
import com.arctos.mochabear.mapdemo.GoogleMapDemoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        val googleMapDemoButton = findViewById<AppCompatButton>(R.id.google_map_demo_button)

        googleMapDemoButton.setOnClickListener {
            val intent = Intent(this, GoogleMapDemoActivity::class.java)
            startActivity(intent)
        }
    }

}