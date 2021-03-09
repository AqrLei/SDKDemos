package com.aqrlei.umengdemo

import android.content.Context
import android.util.Log
import com.umeng.analytics.MobclickAgent
import com.umeng.cconfig.RemoteConfigSettings
import com.umeng.cconfig.UMRemoteConfig
import com.umeng.commonsdk.UMConfigure
import com.umeng.commonsdk.statistics.common.DeviceConfig

/**
 * created by AqrLei on 3/9/21
 */
object UmengManager {
    const val TAG= "UmengManager"

    private const val APP_KEY  = "60472d9c6ee47d382b79132d"
    private const val APP_CHANNEL = "TEST"

    /**
     * [UMConfigure.DEVICE_TYPE_PHONE]
     */
    private const val DEVICE_TYPE = UMConfigure.DEVICE_TYPE_PHONE

    /**
     * Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
     */
    private const val PUSH_SECRET = ""

    /**
     * 预初始化， 在Application.onCreate中调用
     */
    fun preInit(context: Context) {
        UMConfigure.setLogEnabled(true)
        UMRemoteConfig.getInstance().setConfigSettings(RemoteConfigSettings.Builder().setAutoUpdateModeEnabled(true).build())
        UMConfigure.preInit(context, APP_KEY, APP_CHANNEL)
    }

    /**
     * 对于有延迟初始化SDK需求的开发者(不能在Application.onCreate函数中调用UMConfigure.init初始化函数），
     * 必须在Application.onCreate函数中调用UMConfigure.preInit预初始化函数(preInit耗时极少，
     * 不会影响冷启动体验)，而后UMConfigure.init函数可以按需延迟调用(可以放到后台线程中延时调用，可以延迟，
     * 但还是必须调用)。如果您的App已经是在Application.onCreate函数中调用UMConfigure.init进行初始化，
     * 则无需额外调用UMConfigure.preInit预初始化函数。
     */
    fun init(context: Context) {
        UMConfigure.init(context, APP_KEY, APP_CHANNEL, DEVICE_TYPE, PUSH_SECRET)

        // 自动页面采集 Android 4.0及以上版本支持Activity生命周期的自动监控(通过注册自定义callback函数)
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }

    fun onFragmentPageStart(name: String){
        MobclickAgent.onPageStart(name)
    }

    fun onFragmentPageEnd(name: String) {
        MobclickAgent.onPageEnd(name)
    }

    fun getDeviceInfo(context: Context) = run  {
        val deviceId = DeviceConfig.getDeviceIdForGeneral(context)
        val mac = DeviceConfig.getMac(context)
        "deviceId: $deviceId, mac: $mac"
    }

    fun signIn(channel: String, userId: String) {
        MobclickAgent.onProfileSignIn(channel, userId)
    }

    fun signOff() {
        MobclickAgent.onProfileSignOff()
    }
}