package com.example.injection.module;

import android.animation.ArgbEvaluator;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

    private final Application mApplication;

    public AndroidModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    ArgbEvaluator provideArgbEvaluator() {
        return new ArgbEvaluator();
    }

    @Singleton
    @Provides
    Context provideAppContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) mApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    InputMethodManager provideInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Provides
    NotificationManager provideNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    WindowManager provideWindowManager(Context context) {
        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }
}
