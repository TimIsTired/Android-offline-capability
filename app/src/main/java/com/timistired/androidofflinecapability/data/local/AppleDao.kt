package com.timistired.androidofflinecapability.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timistired.androidofflinecapability.data.model.AppleEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface AppleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(apple: AppleEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(apples: List<AppleEntity>): Completable

    @Query("SELECT * FROM AppleEntity WHERE synced = 0")
    fun getNotSynced(): Single<List<AppleEntity>>

    @Query("DELETE FROM AppleEntity WHERE synced = 1")
    fun deleteSynced(): Completable
}