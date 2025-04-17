package com.example.up_01;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Timer timer;
    int seconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressbar);
        onBoardingScreen_1();
    }

    public boolean isInernetAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onBoardingScreen_1(){
        if(isInernetAvailable(this) == true){
            startTimer();
        }
        else{
            progressBar.setProgress(0);
            progressBar.setVisibility(View.GONE);
            startTimer();
        }
    }

    private void startTimer(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, onBoardingScreen_1.class);
                startActivity(intent);
            }
        }, 2000);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(timer != null) timer.cancel();
    }
}