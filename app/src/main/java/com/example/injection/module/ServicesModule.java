package com.example.injection.module;

import com.squareup.moshi.KotlinJsonAdapterFactory;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.com.stackrx.services.questions.service.StackExchangeService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class ServicesModule {

    @Singleton
    @Provides OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build();
    }

    @Singleton
    @Provides
    Moshi provideMoshi() {
        return new Moshi.Builder().add(new KotlinJsonAdapterFactory()).build();
    }

    @Singleton
    @Provides
    StackExchangeService provideStackExchangeService(OkHttpClient client, Moshi moshi) {
        return new StackExchangeService(client, moshi);
    }

}
