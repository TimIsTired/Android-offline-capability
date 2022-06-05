package com.timistired.androidofflinecapability.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.timistired.androidofflinecapability.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel by viewModels()
        findViewById<Button>(R.id.button1).setOnClickListener {
            viewModel.createData()
        }
    }
}