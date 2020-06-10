package com.warriorsdev.lyricsovh.application

import android.app.Application
import com.warriorsdev.lyricsovh.module.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LyricApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LyricApplication)
            modules(appModules)
        }
    }
}