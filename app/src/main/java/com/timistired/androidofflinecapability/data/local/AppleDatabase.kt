package com.timistired.androidofflinecapability.data.local

import androidx.room.RoomDatabase
import com.timistired.androidofflinecapability.data.model.AppleEntity

@androidx.room.Database(
    entities = [AppleEntity::class],
    version = 1
)
abstract class AppleDatabase : RoomDatabase() {
    abstract fun appleDao(): AppleDao
}