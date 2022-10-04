package com.zeller.database.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PersistedBalance() : RealmObject {
    @PrimaryKey
    var id: String = "balance"
    var balance: Float = 0f

    constructor(id: String, balance: Float) : this() {
        this.id = id
        this.balance = balance
    }
}