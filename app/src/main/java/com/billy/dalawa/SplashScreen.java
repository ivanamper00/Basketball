package com.billy.dalawa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.billy.dalawa.controller.MainController;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


public class SplashScreen extends AppCompatActivity {
    MainController controller;
    ImageView splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        controller = new MainController(this);
        splash();
        main();
    }

    public void main(){
        controller.clearContents();
        controller.saveAllQueries();
        controller.loopSplash();
    }

    public void splash(){
        splash = findViewById(R.id.splash);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(splash);
        Glide.with(this).load(R.drawable.splash).into(imageViewTarget);
    }
}