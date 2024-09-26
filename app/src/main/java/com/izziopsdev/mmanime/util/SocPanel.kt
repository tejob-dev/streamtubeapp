package com.izziopsdev.mmanime.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.izziopsdev.mmanime.R


class SocPanel {

    private var alBuild: AlertDialog? = null
    private var appdname: String = "com.facebook.orca"

    fun showDialog(activity: AppCompatActivity) {
        val prf = PrefManager(activity)
        val socialView: View = LayoutInflater.from(activity).inflate(R.layout.list_social_item, null, false)
        val what: ImageView = socialView.findViewById(R.id.btn_wha)
        val twit: ImageView = socialView.findViewById(R.id.btn_twi)
        val insta: ImageView = socialView.findViewById(R.id.btn_instag)
        val skypp: ImageView = socialView.findViewById(R.id.btn_skyp)
        val tgram: ImageView = socialView.findViewById(R.id.btn_telegr)
        val faceb: ImageView = socialView.findViewById(R.id.btn_faceb)
        faceb.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                appdname = "com.facebook.orca"
                val pm = activity.packageManager
                try {
                    val info: PackageInfo =
                        pm.getPackageInfo(appdname, PackageManager.GET_META_DATA)
                } catch (e: PackageManager.NameNotFoundException) {
                    appdname = "com.facebook.katana"
                    try {
                        val info: PackageInfo =
                            pm.getPackageInfo(appdname, PackageManager.GET_META_DATA)
                    } catch (es: PackageManager.NameNotFoundException) {
                        appdname = "com.facebook.lite"
                    }
                }
                sendShareToSocApp(activity, appdname)
                alBuild!!.dismiss()
            }
        })
        what.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                appdname = "com.whatsapp"
                sendShareToSocApp(activity, appdname)
                alBuild!!.dismiss()
            }
        })
        tgram.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                appdname = "org.telegram.messenger.web"
                sendShareToSocApp(activity, appdname)
                alBuild!!.dismiss()
            }
        })
        insta.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                appdname = "com.instagram.android"
                sendShareToSocApp(activity, appdname)
                alBuild!!.dismiss()
            }
        })
        skypp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                appdname = "com.skype.raider"
                sendShareToSocApp(activity, appdname)
                alBuild!!.dismiss()
            }
        })
        twit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                appdname = "com.twitter.android"
                sendShareToSocApp(activity, appdname)
                alBuild!!.dismiss()
            }
        })
        alBuild = AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert).create()
        alBuild!!.setIcon(R.mipmap.ic_launcher_round)
        alBuild!!.setTitle(activity.getString(R.string.hint_share_panel))
        alBuild!!.setMessage(activity.getString(R.string.share_messageSave).format(activity.getString(R.string.app_name)))

//        if(prf.getBoolean("is_share_one") == true) {
//            alBuild!!.setButton(activity.getString(R.string.cancel), DialogInterface.OnClickListener { dialogInterface, i ->
//                alBuild!!.dismiss()
//            } )
//            alBuild!!.setCancelable(true)
//        }else {
//            alBuild!!.setCancelable(false)
//            prf.setBoolean("is_share_one", true)
//        }

        alBuild!!.setView(socialView)
        alBuild!!.show()
    }


    @SuppressLint("StringFormatInvalid")
    private fun sendShareToSocApp(activity: AppCompatActivity, appdname: String) {
        val prf = PrefManager(activity)

        val sharUrl: String = "https://play.google.com/store/apps/details?id="+activity.packageName //prf.getString("app_store_link")
        val text: String = activity.getString(R.string.soc_share_messageSave).format(activity.getString(R.string.app_name), sharUrl)
        val pm = activity.packageManager
        try {
            val waIntent = Intent(Intent.ACTION_SEND)
            waIntent.type = "text/plain"
            val info = pm.getPackageInfo(appdname, PackageManager.GET_META_DATA)
            waIntent.setPackage(appdname)
            waIntent.putExtra(Intent.EXTRA_TEXT, text)
            activity.startActivity(
                Intent.createChooser(
                    waIntent,
                    activity.getString(R.string.app_name)
                )
            )
        } catch (e: PackageManager.NameNotFoundException) {
            //System.out.println("WhatsApp not Installed");
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_SUBJECT,
                activity.getString(R.string.app_name) + activity.getString(R.string.vos_manga_et_manhwa_en_stream)
            )
            intent.putExtra(Intent.EXTRA_TEXT, text)
            activity.startActivity(
                Intent.createChooser(
                    intent,
                    activity.getString(R.string.app_name)
                )
            )
        }
    }

}