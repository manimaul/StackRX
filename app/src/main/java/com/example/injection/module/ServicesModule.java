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

    private final Application mApplication;

    public ServicesModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    StackExchangeService provideQuestionsDAO() {
        return new StackExchangeService();
    }

    @Provides
    SharedPreferences providePreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }

    @Provides
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) mApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

}
