package com.example.injection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.com.stackrx.services.questions.service.StackExchangeService;

@Module
public class ServicesModule {

    private final Application application;

    public ServicesModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    StackExchangeService provideQuestionsDAO() {
        return new StackExchangeService();
    }

    @Provides
    SharedPreferences providePreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

}
