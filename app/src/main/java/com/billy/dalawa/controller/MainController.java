package com.billy.dalawa.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.billy.dalawa.controller.activity.MainActivity;
import com.billy.dalawa.database.MainApi;
import com.billy.dalawa.model.Events;
import com.billy.dalawa.model.Leagues;
import com.billy.dalawa.model.Players;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class MainController {

    public static final String SHARED_PREFS = "sharedPrefs";

    public static final String PLAYERS = "players";
    public static final String PLAYERS_ERROR = "players_err";

    public static final String MATCHES = "matches";
    public static final String MATCHES_ERROR = "matches_err";

    public static final String LEAGUES = "leagues";
    public static final String LEAGUES_ERROR = "leagues_err";

    public static final String LIVE_MATCHES = "live_matches";
    public static final String LIVE_MATCHES_ERROR = "live_matches_err";

    public static final String CURRENT_LEAGUE = "current_leagues";


    ProgressDialog pdLoading;
    CountDownTimer countDownTimer;
    Context context;
    Intent intent;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    HashMap<String, String> errors;
    int flag;

    @SuppressLint("CommitPrefEdits")
    public MainController(Context context){
        this.pdLoading = new ProgressDialog(context);
        this.context = context;
        this.activity = (Activity) context;
        this.intent = activity.getIntent();
        this.sharedPreferences = this.context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        this.flag = 0;
    }

    public String getErrors(String key) {
        return sharedPreferences.getString(key,"");
    }

    public void setErrors(String key, String value) {
        editor.putString(key ,value);
        editor.commit();
    }

    public String getDefaultLeague(){
        return sharedPreferences.getString(CURRENT_LEAGUE,"4328");
    }
    public void setDefaultLeague(String league){
        editor.putString(CURRENT_LEAGUE ,league);
        editor.commit();
    }

    public void clearContents(){
        this.editor.clear();
        this.editor.commit();
    }

    //Next Intent w/ Data Function
    public void NextIntent(Class toClass, String data) {
        Intent intent = new Intent(context, toClass);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    //Next Intent w/o Data Function
    public void NextIntent(Class toClass) {
        NextIntent(toClass, "");
    }

    //Retrofit Builder Function
    public MainApi BasketballRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainApi.BASKETBALL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MainApi api = retrofit.create(MainApi.class);
        return api;
    }
    public MainApi MediaRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainApi.MEDIA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MainApi api = retrofit.create(MainApi.class);
        return api;
    }



    // Loading Function
    public void startLoading(String text){
        pdLoading.setMessage("\t" + text);
        pdLoading.setCancelable(false);
        pdLoading.show();
    }
    public void startLoading(){
        startLoading("Please Wait...");
    }
    public void stopLoading(){
        pdLoading.dismiss();
    }


    public <T> void saveData(String calling, List<T> object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(calling ,json);
        editor.commit();
    }

    public List<Players.Datum> retrievePlayers(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(PLAYERS,"");
        Type type = new TypeToken<List<Players.Datum>>(){}.getType();
        List<Players.Datum> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<Events.Datum> retrieveEvents(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(MATCHES,"");
        Type type = new TypeToken<List<Events.Datum>>(){}.getType();
        List<Events.Datum> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<Leagues.Datum> retrieveLeagues(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LEAGUES,"");
        Type type = new TypeToken<List<Leagues.Datum>>(){}.getType();
        List<Leagues.Datum> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<Events.Datum> retrieveLiveMatches(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LIVE_MATCHES,"");
        Type type = new TypeToken<List<Events.Datum>>(){}.getType();
        List<Events.Datum> objects = gson.fromJson(json, type);
        return objects;
    }

    public void savePlayers(){
        Call<Players> call = BasketballRetrofit().getPlayers();

        call.enqueue(new Callback<Players>(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Players> call, retrofit2.Response<Players> response) {
                Players playersBody = response.body();
                List<Players.Datum> playersData = playersBody.getData();
                saveData(PLAYERS, playersData);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Players Saved!");
            }
            @Override
            public void onFailure(Call<Players> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(PLAYERS_ERROR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+ t.getMessage());
            }
        });
    }

    public void saveEvents(){
        Call<Events> call = BasketballRetrofit().getEvent();

        call.enqueue(new Callback<Events>(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Events> call, retrofit2.Response<Events> response) {
                Events matchesBody = response.body();
                List<Events.Datum> matches = matchesBody.getData();
                saveData(MATCHES, matches);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Matches Saved!");
            }
            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(MATCHES_ERROR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+ t.getMessage());
            }
        });
    }

    public void saveLeagues(){
        Call<Leagues> call = BasketballRetrofit().getLeagues();

        call.enqueue(new Callback<Leagues>(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Leagues> call, retrofit2.Response<Leagues> response) {
                Leagues leaguesBody = response.body();
                List<Leagues.Datum> leagues = leaguesBody.getData();
                saveData(LEAGUES, leagues);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Leagues Saved!");
            }
            @Override
            public void onFailure(Call<Leagues> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(LEAGUES_ERROR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+ t.getMessage());
            }
        });
    }

    public void saveLiveMatches(){
        Call<Events> call = BasketballRetrofit().getLive();

        call.enqueue(new Callback<Events>(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Events> call, retrofit2.Response<Events> response) {
                Events livesBody = response.body();
                List<Events.Datum> Lives = livesBody.getData();
                saveData(LIVE_MATCHES, Lives);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Live Matches Saved!");
            }
            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(LIVE_MATCHES_ERROR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+ t.getMessage());
            }
        });
    }


//
    public void loop(){
        checkData();
    }

    public void checkData(){
        countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(retrieveLeagues() == null ||
                        retrievePlayers() == null ||
                        retrieveLiveMatches() == null ||
                        retrieveEvents() == null){
                    errorChecker();
                    loop();
                }else{
                    NextIntent(MainActivity.class);
                    stopLoading();
                }

            }
        }.start();
    }
    public void loopSplash(){
        checkDataSplash();
    }
    public void checkDataSplash(){
        countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                if(retrieveLeagues() == null ||
                        retrievePlayers() == null ||
                        retrieveLiveMatches() == null ||
                        retrieveEvents() == null){
                    errorChecker();
                    loopSplash();
                }else{
                    NextIntent(MainActivity.class);
                    activity.finish();
                }
            }
        }.start();
    }


    public void errorChecker(){
        if(getErrors(MATCHES_ERROR).contains("timeout")){
            saveEvents();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Events");
            setErrors(MATCHES_ERROR,"");
        }else if(!getErrors(MATCHES_ERROR).isEmpty()){
            errorHolder();
        }
        if(getErrors(LEAGUES_ERROR).contains("timeout")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Leagues");
            saveLeagues();
            setErrors(LEAGUES_ERROR,"");
        }else if(!getErrors(LEAGUES_ERROR).isEmpty()){
            errorHolder();
        }
        if(getErrors(PLAYERS_ERROR).contains("timeout")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Upcoming");
            savePlayers();
            setErrors(PLAYERS_ERROR,"");
        }else if(!getErrors(PLAYERS_ERROR).isEmpty()){
            errorHolder();
        }
        if(getErrors(LIVE_MATCHES_ERROR).contains("timeout")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Highlights");
            saveLiveMatches();
            setErrors(LIVE_MATCHES_ERROR,"");
        }else if(!getErrors(LIVE_MATCHES_ERROR).isEmpty()){
            errorHolder();
        }
    }

    public void errorHolder(){
        if(flag == 0){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.context);
            alertDialog.setCancelable(false);
            alertDialog.setMessage("Failed To Connect, Try To Restart the Application!");
            alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            AlertDialog alert = alertDialog.create();
            alert.show();
            flag++;
        }
    }

    public void saveAllQueries(){
        saveEvents();
        saveLeagues();
        saveLiveMatches();
        savePlayers();
    }

}
