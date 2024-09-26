package com.izziopsdev.mmanime;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.izziopsdev.mmanime.R;
import com.izziopsdev.mmanime.util.Config;
import com.izziopsdev.mmanime.util.FetchData;
import com.izziopsdev.mmanime.util.Helping;
import com.izziopsdev.mmanime.util.PrefManager;
import com.izziopsdev.mmanime.util.StringUtilKt;
import com.izziopsdev.mmanime.util.models.MissVersion;

import java.util.Date;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

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
            try {
                AsyncTask appVersIo = new FetchData.AppVersion(this, Helping.Companion.getDecodeUpdate().toString(), new FetchData.LibraryListener(){

                    @Override
                    public void onCompleted(MissVersion vers, Boolean iscomplete) {
                        if(iscomplete!=null){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if(iscomplete) {

                                        Helping.Companion.showMessage(
                                                "Message !",
                                                Helping.Companion.toModifString(vers.getReleaseNotes(), true, "\n", ""),
                                                SplashScreen.this,
                                                false,
                                                new Function0<Unit>() {
                                                    @Override
                                                    public Unit invoke() {
                                                        Helping.Companion.openUrl(SplashScreen.this, vers.getUrl().toString());
                                                        return null;
                                                    }
                                                },
                                                new Function0<Unit>() {
                                                    @Override
                                                    public Unit invoke() {
                                                        showDialog(SplashScreen.this, getString(R.string.advertisement), getString(R.string.vous_ne_pouvez_pas_continuer_sans_faire_de_mise_jour), true);
                                                        return null;
                                                    }
                                                },
                                                SplashScreen.this.getString(R.string.allez_sur_le_store),
                                                true,
                                                false
                                        );
                                    }else {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                loadHome();
                                            }
                                        }, 3000);
                                    }
                                }
                            });
                        }else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loadHome();
                                }
                            }, 3000);
                        }
                    }
                });
                appVersIo.execute();
            }catch (Exception ex){
                Log.d("Error req", ex.getMessage());
            }
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