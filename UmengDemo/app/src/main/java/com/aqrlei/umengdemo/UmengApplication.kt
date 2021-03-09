package com.aqrlei.umengdemo

import android.app.Application

/**
 * created by AqrLei on 3/9/21
 */
class UmengApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        UmengManager.preInit(this)
    }
}