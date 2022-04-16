package com.admin_official.contacts

import android.Manifest.permission.READ_CONTACTS
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.admin_official.contacts.databinding.ActivityMainBinding
import java.util.*

const val REQUEST_CODE_READ_CONTACTS = 1

var hasPermission = false

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RVListener{

    private lateinit var binding: ActivityMainBinding

    val viewModel: AppViewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // setting action bar
        setSupportActionBar(binding.toolbarMain)

        // permissions
        val hasReadContactsPermission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)

        hasPermission = (hasReadContactsPermission != PackageManager.PERMISSION_DENIED)

        if(hasReadContactsPermission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_CONTACTS), REQUEST_CODE_READ_CONTACTS)
        }

        if(hasPermission) viewModel.download();
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == REQUEST_CODE_READ_CONTACTS) {
            hasPermission = true
            viewModel.download()
        }
    }

    override fun onItemClicked(contest: Contact) {
        // todo
    }
}