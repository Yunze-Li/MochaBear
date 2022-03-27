package com.arctos.mochabear.activitydemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arctos.mochabear.R

class LeakingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaking)
        TestDataModel.get().activityList.add(this)
    }
}