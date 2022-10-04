package com.zeller.database.model

import io.realm.kotlin.types.RealmObject

class PersistedTransaction() : RealmObject {
    var amount: Float = 0f
    var isDeposit: Boolean = false

    constructor(amount: Float, isDeposit: Boolean) : this() {
        this.amount = amount
        this.isDeposit = isDeposit
    }
}