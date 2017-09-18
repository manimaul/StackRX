package com.example.main.application;

import android.app.Application;

import com.example.injection.Injector;

public class StackRXApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.buildApplicationComponent(this);
    }
}
