package com.example.injection.module

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import example.com.stackrx.services.questions.service.StackExchangeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class ServicesModule {

    @Singleton
    @Provides
    internal fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build()

    @Singleton
    @Provides
    internal fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    internal fun provideStackExchangeService(client: OkHttpClient, moshi: Moshi): StackExchangeService =
            StackExchangeService(client, moshi)

}
