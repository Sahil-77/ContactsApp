package com.admin_official.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.admin_official.contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // setting action bar
        setSupportActionBar(binding.toolbarMain)
    }
}