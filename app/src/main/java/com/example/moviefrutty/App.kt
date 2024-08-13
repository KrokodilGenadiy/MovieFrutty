package com.example.moviefrutty

import android.app.Application
import com.zaus_app.moviefrumy_new.di.AppComponent
import com.zaus_app.moviefrumy_new.di.modules.DomainModule
import com.zaus_app.moviefrumy_new.di.modules.RemoteModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
}