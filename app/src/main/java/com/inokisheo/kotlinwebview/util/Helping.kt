package com.inokisheo.kotlinwebview.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build


class Helping {

    companion object{

        fun appInstalled(activity: Activity, dname: String?): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val pm = activity.packageManager
                val io = Intent(Intent.ACTION_VIEW)
                if (io.resolveActivity(pm) != null) {
                    try {
                        pm.getPackageInfo(dname!!, PackageManager.GET_ACTIVITIES)
                        return true
                    } catch (e: PackageManager.NameNotFoundException) {
                        e.printStackTrace()
                        println("An error for check installed app " + e.message)
                    }
                }
            } else {
                val pm = activity.packageManager
                try {
                    pm.getPackageInfo(dname!!, PackageManager.GET_ACTIVITIES)
                    return true
                } catch (e: PackageManager.NameNotFoundException) {
                    println("An error for check installed app " + e.message)
                }
            }
            return false
        }
    }


    fun checkDelete(activity: Activity, dname: String?){
        if (appInstalled(activity, dname)) {
            val intent = Intent(Intent.ACTION_DELETE)
            intent.data = Uri.parse("package:$dname")
            activity.startActivityForResult(intent, 100)
        }
    }

}