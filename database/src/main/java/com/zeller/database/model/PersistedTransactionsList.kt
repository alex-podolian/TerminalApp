package com.zeller.database.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PersistedTransactionsList() : RealmObject {
    @PrimaryKey
    var id: String = "transactionsList"
    var transactions: RealmList<PersistedTransaction> = realmListOf()

    constructor(id: String, transactions: RealmList<PersistedTransaction>) : this() {
        this.id = id
        this.transactions = transactions
    }
}