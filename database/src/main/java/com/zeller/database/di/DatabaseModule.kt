package com.zeller.database.di

import com.zeller.data.repository.LocalSource
import com.zeller.database.DatabaseSource
import com.zeller.database.model.PersistedBalance
import com.zeller.database.model.PersistedTransaction
import com.zeller.database.model.PersistedTransactionsList
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    @Singleton
    @Binds
    abstract fun provideLocalSource(storage: DatabaseSource): LocalSource

    companion object {
        @Singleton
        @Provides
        fun provideDatabase(): Realm {
            return Realm.open(
                RealmConfiguration.Builder(
                    schema = setOf(
                        PersistedBalance::class,
                        PersistedTransaction::class,
                        PersistedTransactionsList::class,
                    )
                ).build()
            )
        }
    }
}