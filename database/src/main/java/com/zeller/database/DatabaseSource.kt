package com.zeller.database

import com.zeller.data.local.model.LocalBalance
import com.zeller.data.local.model.LocalTransactionsList
import com.zeller.data.repository.LocalSource
import com.zeller.database.mapper.toDatabase
import com.zeller.database.mapper.toLocal
import com.zeller.database.model.PersistedBalance
import com.zeller.database.model.PersistedTransactionsList
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseSource @Inject constructor(
    private val realm: Realm,
) : LocalSource {

    override suspend fun retrieveBalance(id: String): LocalBalance? {
        return realm.query(PersistedBalance::class, "id CONTAINS '$id'")
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

    override suspend fun retrieveTransactions(id: String): LocalTransactionsList? {
        return realm.query(PersistedTransactionsList::class, "id CONTAINS '$id'")
            .first()
            .find()
            ?.toLocal()
    }

    override suspend fun persistTransaction(localTransactionsList: LocalTransactionsList) {
        withContext(Dispatchers.IO) {
            realm.write {
                val persistedTransactionsList: PersistedTransactionsList? =
                    this.query<PersistedTransactionsList>("id = '${localTransactionsList.id}'")
                        .first().find()
                if (persistedTransactionsList != null) {
                    persistedTransactionsList.transactions =
                        localTransactionsList.transactions.map { it.toDatabase() }.toRealmList()
                } else {
                    copyToRealm(localTransactionsList.toDatabase())
                }
            }
        }
    }
}