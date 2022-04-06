package com.arctos.mochabear.viewmodeldemo

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.arctos.mochabear.R

class ViewModelDemoActivity : AppCompatActivity() {

    private lateinit var viewModel: CustomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_demo)

        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        val calculateButton = findViewById<AppCompatButton>(R.id.calculate_button)
        calculateButton.setOnClickListener {
            viewModel.fetchResult(input1.text.toString().toInt(), input2.text.toString().toInt())
        }

        val resultText = findViewById<TextView>(R.id.result_text)
        viewModel = ViewModelProvider(this).get(CustomViewModel::class.java)
        viewModel.resultLiveData.observe(this) {
            resultText.text = it.toString()
        }
    }
}