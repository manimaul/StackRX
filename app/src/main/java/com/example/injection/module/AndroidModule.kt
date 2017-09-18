package com.example.injection.module

import android.animation.ArgbEvaluator
import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val mApplication: Application) {

    @Singleton
    @Provides
    internal fun provideApplication(): Application = mApplication

    @Provides
    internal fun provideArgbEvaluator(): ArgbEvaluator = ArgbEvaluator()

    @Singleton
    @Provides
    internal fun provideAppContext(): Context = mApplication.applicationContext

    @Provides
    internal fun provideConnectivityManager(): ConnectivityManager =
            mApplication.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    internal fun provideInputMethodManager(context: Context): InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    @Provides
    internal fun provideNotificationManager(context: Context): NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @Provides
    internal fun provideResources(context: Context): Resources = context.resources

    @Provides
    internal fun provideSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    internal fun provideWindowManager(context: Context): WindowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
}
