package com.example.developer.qrkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScanningClass : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private lateinit var mScannerView: ZBarScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZBarScannerView(this)
        setContentView(mScannerView)
    }
    override fun handleResult(result: Result?) {
        Toast.makeText(this, result?.contents, Toast.LENGTH_SHORT).show()

        //Camera will stop after scanning result, so we need to resume the
        //preview in order scan more codes
        val intent = Intent()
        intent.putExtra("qr",result?.contents)
        setResult(Activity.RESULT_OK,intent)
        finish()
        //mScannerView.resumeCameraPreview(this)
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }
}