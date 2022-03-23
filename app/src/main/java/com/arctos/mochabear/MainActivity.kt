package com.arctos.mochabear

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.arctos.mochabear.activitydemo.ActivityDemoActivity
import com.arctos.mochabear.servicedemo.ServiceDemoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Activity Demo
        val activityDemoButton = findViewById<AppCompatButton>(R.id.activity_demo_button)
        activityDemoButton.setOnClickListener {
            val intent = Intent(this, ActivityDemoActivity::class.java)
            startActivity(intent)
        }

        // Service Demo
        val serviceDemoButton = findViewById<AppCompatButton>(R.id.service_demo_button)
        serviceDemoButton.setOnClickListener {
            val intent = Intent(this, ServiceDemoActivity::class.java)
            startActivity(intent)
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
}