package com.timistired.androidofflinecapability.data.local

import com.timistired.androidofflinecapability.data.model.AppleEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

object AppleLocalDataSource {

    private lateinit var database: AppleDatabase

    fun setDatabase(database: AppleDatabase) {
        this.database = database
    }

    fun saveApple(apple: AppleEntity): Completable {
        return database.appleDao().insert(apple)
    }

    fun saveApples(apples: List<AppleEntity>): Completable {
        return database.appleDao().insertMultiple(apples)
    }

    fun getNotSyncedApples(): Single<List<AppleEntity>> {
        return database.appleDao().getNotSynced()
    }

    fun deleteSyncedApples(): Completable {
        return database.appleDao().deleteSynced()
    }
}