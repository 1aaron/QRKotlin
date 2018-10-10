package com.example.developer.qrkotlin

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import java.io.File

class MainActivity : AppCompatActivity() {


    private val LOG_TAG = "MAinActivity"
    private val REQUEST_WRITE_PERMISSION = 1
    private lateinit var mScannerView: ZBarScannerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScan.setOnClickListener {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), REQUEST_WRITE_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_WRITE_PERMISSION -> {
                Log.e("MI ACTIVIDAD","${grantResults[0]}")
                Log.e("MI ACTIVIDAD","${Activity.RESULT_OK}")
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startActivityForResult(Intent(this@MainActivity,ScanningClass::class.java),1)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            1 -> {
                if (resultCode == Activity.RESULT_OK) {
                    txt.text = data?.getStringExtra("qr")
                }
            }
        }
    }
}
