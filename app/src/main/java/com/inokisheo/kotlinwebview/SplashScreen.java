package com.inokisheo.kotlinwebview;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.enums.AppUpdaterError;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.github.javiersantos.appupdater.objects.Update;
import com.inokisheo.kotlinwebview.R;
import com.inokisheo.kotlinwebview.util.Config;
import com.inokisheo.kotlinwebview.util.EncWolker;
import com.inokisheo.kotlinwebview.util.PrefManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class SplashScreen extends AppCompatActivity {

    private PrefManager prf;
    private boolean isError = false;
    private boolean secPassed = false;

    //https://docs.google.com/forms/d/e/1FAIpQLSe2GgDt8E76S_44KRzZMuV7-I7RnQMdU7V8ZczWoXWQ15fB8Q/formResponse
    //entry.1805816549: tchimouj66@gmail.com
    //entry.790670239: 4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        prf = new PrefManager(this);

        if(Config.isNetWorkOk(this)){

            AppUpdater appUpdater = new AppUpdater(this);
            appUpdater.setDisplay(Display.DIALOG);
            appUpdater.setUpdateFrom(UpdateFrom.JSON)
                    .setContentOnUpdateNotAvailable(getString(R.string.vous_avez_la_derni_re_version_disponible_de_l_application))
                    .setUpdateJSON(Config.update_url)
                    .setButtonDismiss(getString(R.string.plus_tard))
                    .setButtonDoNotShowAgainClickListener(new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            showDialog(SplashScreen.this, getString(R.string.advertisement), getString(R.string.vous_ne_pouvez_pas_continuer_sans_faire_de_mise_jour), true);
                        }
                    });
//        appUpdater.showEvery(5)
//        appUpdater.showAppUpdated(true) //TEST MODE
            appUpdater.setButtonUpdate(getString(R.string.allez_sur_le_store)); // Notification icon
            appUpdater.setIcon(R.mipmap.ic_launcher); // Notification icon
            appUpdater.setCancelable(false); // Dialog could not be dismissable
            appUpdater.start();

            AppUpdaterUtils appUpdaterUtils = new AppUpdaterUtils(this)
                    //.setUpdateFrom(UpdateFrom.AMAZON)
                    //.setUpdateFrom(UpdateFrom.GITHUB)
                    //.setGitHubUserAndRepo("javiersantos", "AppUpdater")
                    //...
                    .setUpdateFrom(UpdateFrom.JSON)
                    .setUpdateJSON(Config.update_url)
                    .withListener(new AppUpdaterUtils.UpdateListener() {
                        @Override
                        public void onSuccess(Update update, Boolean isUpdateAvailable) {
//                            Log.d("Latest Version", update.getLatestVersion());
//                            Log.d("Latest Version Code", update.getLatestVersionCode());
//                            Log.d("Release notes", update.getReleaseNotes());
//                            Log.d("URL", update.getUrlToDownload());
//                            Log.d("Is update available?", Boolean.toString(isUpdateAvailable));
                            if(isUpdateAvailable == false){
                                //showDialog(SplashScreen.this, getString(R.string.advertisement), "Echec de la vérification de la mise à jour !", false);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadHome();
                                    }
                                }, 3000);
                            }
                        }

                        @Override
                        public void onFailed(AppUpdaterError error) {
//                            Log.d("AppUpdater Error", "Something went wrong");
                            showDialog(SplashScreen.this, getString(R.string.advertisement), "Echec de la vérification de la mise à jour !", false);
                        }
                    });
            appUpdaterUtils.start();

        }else{
            showDialog(this, getString(R.string.advertisement), getString(R.string.veuillez_activer_votre_acc_s_internet), false);
        }

    }

    private boolean isMomentToLoad(String day_api) {

        if(day_api.equals("")) return true;

        Date date = new Date();
        int jour = date.getDate();
        int heur = date.getHours();
        String[] list = day_api.split("_");
        if ( Integer.valueOf(list[0]) != jour ) {
            return true;
        }else{

            if ( (heur - Integer.valueOf(list[1])) >= 6 ) {
                return true;
            }

        }

        return false;
    }

    private void loadHome() {

        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        SplashScreen.this.finish();

    }

    private void showDialog(Context mainActivity, String title, String message, Boolean close) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.hint_btn_close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(Config.isNetWorkOk(SplashScreen.this)){
                   if(close == false) loadHome();
                   else SplashScreen.this.finish();
                }else{
                    showDialog(SplashScreen.this, getString(R.string.advertisement), getString(R.string.veuillez_activer_votre_acc_s_internet), false);
                }
            }
        });
        builder.create().show();
    }
}