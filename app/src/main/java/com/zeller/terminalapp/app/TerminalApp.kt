package com.zeller.terminalapp.app

import android.app.Application
import com.zeller.terminalapp.di.AppComponent
import com.zeller.terminalapp.di.DaggerAppComponent

open class TerminalApp: Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext, this)
    }
}