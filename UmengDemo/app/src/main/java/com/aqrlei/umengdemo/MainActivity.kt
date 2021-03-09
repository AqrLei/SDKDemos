package com.aqrlei.umengdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        thread {
            UmengManager.init(this.applicationContext)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(UmengManager.TAG, UmengManager.getDeviceInfo(this.applicationContext))
        UmengManager.signIn("TEST","123456")
    }

    override fun onDestroy() {
        super.onDestroy()
        UmengManager.signOff()
    }
}