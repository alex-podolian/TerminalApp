package com.zeller.database

import com.zeller.data.local.model.LocalBalance
import com.zeller.data.repository.LocalSource
import com.zeller.database.mapper.toDatabase
import com.zeller.database.mapper.toLocal
import com.zeller.database.model.PersistedBalance
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseSource @Inject constructor(
    private val realm: Realm,
) : LocalSource {

    override suspend fun retrieveBalance(): LocalBalance? {
        return realm.query(PersistedBalance::class)
            .first()
            .find()
            ?.toLocal()
    }

    override suspend fun persistBalance(localBalance: LocalBalance) {
        withContext(Dispatchers.IO) {
            realm.write {
                val persistedBalance: PersistedBalance? =
                    this.query<PersistedBalance>("id = '${localBalance.id}'").first().find()
                if (persistedBalance != null) {
                    persistedBalance.balance = localBalance.balance
                } else {
                    copyToRealm(localBalance.toDatabase())
                }
            }
        }
    }
}