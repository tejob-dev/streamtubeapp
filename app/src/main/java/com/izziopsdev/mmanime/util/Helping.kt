package com.izziopsdev.mmanime.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.PackageInfoCompat
import com.izziopsdev.mmanime.R


class Helping {

    companion object{
        @SuppressLint("NewApi")
        var decodeUpdate = String(java.util.Base64.getDecoder().decode(Config.update_url_enc.toByteArray()))

        val urlMain = "https://anime-manga-manhwa.com/"

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

        fun toModifString(list: List<String>?, isComma:Boolean = true, commaReplace:String = "", prefix:String = ""): String {
            val list = list?.map {
                var dani = "${it}"
                if(prefix.isNotEmpty()) dani = "${prefix} ${it}"
                dani
            }
            val values = list.toString().replace("]", "").replace("[", "")
            return if(isComma) values.replace(", ", commaReplace) else values
        }

        fun openUrl(ctx: Context, link: String){
            val url = link
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }

            if (intent.resolveActivity(ctx.packageManager) != null) {
                ctx.startActivity(intent)
            }
        }

        fun showMessage(title: String, message: String, context: Context, finished: Boolean = false, callback: () -> Unit?, callbackNo: () -> Unit?, positive: String? = "Oui", showNo: Boolean = false, consent: Boolean = false) {
            try {
                val builder = AlertDialog.Builder(context, R.style.DialogTheme)
                // Display a message on alert dialog
                builder.setTitle(title)
                builder.setIcon(R.mipmap.ic_launcher_round)
                builder.setMessage(message)
                builder.setCancelable(false)

                // Set a positive button and its click listener on alert dialog
                builder.setPositiveButton(positive) { dialog, _ ->
                    dialog.dismiss()

                    if (callback != null) callback()

                    if (finished) {
                        //callback
                        (context as AppCompatActivity).finish()
                    }
                }

                if (showNo) {
                    builder.setNegativeButton("Annuler !") { dialog, _ ->
                        dialog.dismiss()

                        callbackNo.invoke()
//                        if (consent) {
                            //callback
//                            (context as AppCompatActivity).finish()
//                        }
                    }
                }

                // Finally, make the alert dialog using builder
                val dialog: AlertDialog = builder.create()

                // Display the alert dialog on app interface
                dialog.show()
            } catch (ex: Exception) {
                Log.e("Error Dialog", ex.message.toString())
                //FirebaseCrashlytics.getInstance().recordException(ex)
            }
        }

        fun getAppCuVesCod(ctx: Context): Long {
            val versionCode: Long = try {
                val packageInfo = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
                PackageInfoCompat.getLongVersionCode(packageInfo)
            } catch (e: PackageManager.NameNotFoundException) {
                0L // Valeur par défaut si une erreur survient
            }

            return versionCode
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