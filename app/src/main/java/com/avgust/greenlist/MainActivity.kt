package com.avgust.greenlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.avgust.greenlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //viewBinding
    private lateinit var binding: ActivityMainBinding

    //NavController
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}