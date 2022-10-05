package com.zeller.terminalapp.di

import android.app.Application
import android.content.Context
import com.zeller.data.di.DataModule
import com.zeller.database.di.DatabaseModule
import com.zeller.network.di.NetworkModule
import com.zeller.terminalapp.presentation.main.MainActivity
import com.zeller.terminalapp.presentation.transactions.TransactionsActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [DataModule::class, DatabaseModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, @BindsInstance app: Application): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: TransactionsActivity)
}
