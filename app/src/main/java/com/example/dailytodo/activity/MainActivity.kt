package com.example.dailytodo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dailytodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


    }



}