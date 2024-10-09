package com.izziopsdev.mmanime;

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//import cat.ereza.customactivityoncrash.CustomActivityOnCrash
//import cat.ereza.customactivityoncrash.config.CaocConfig
//import io.monedata.Monedata.Consent
//import io.monedata.Monedata.initialize
//import io.monedata.Monedata.isInitialized
//import io.monedata.Monedata.start


class  Appli: MultiDexApplication(), Configuration.Provider{

    //private val MONID = "ef5cd687-dbcb-42e0-a42f-10d9923e9333"
    private val ONESIGNAL_APP_ID = "9a374172-41c0-4fed-afb4-42751ffb7d35"

    companion object{
        lateinit var CONTEXTGB: Context
    }

    @SuppressLint("RestrictedApi")
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
//        CustomActivityOnCrash.install(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        CONTEXTGB = this

        MultiDex.install(this)

        // Verbose Logging set to help debug issues, remove before releasing your app.
        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(false)
        }

//        CaocConfig.Builder.create().apply()

//        if (!isInitialized) {
//            initialize(this, MONID, true) { isReady: Boolean? ->
//                // This will be called when the SDK has initialized
//                start(this)
//                Consent.set(this, true)
//                Unit
//            }
//        } else {
//            //Toast.makeText(getApplicationContext(), "Monedata not initiate or of course init", Toast.LENGTH_LONG).show();
//        }
        // Enable verbose OneSignal logging to debug issues if needed.
//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
//        // OneSignal Initialization
//        OneSignal.initWithContext(getApplicationContext());
//        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }

//    override fun getWorkManagerConfiguration(): Configuration {
//        return Configuration.Builder()
//            .build()
//    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .build()
//        get() = TODO("Not yet implemented")


}