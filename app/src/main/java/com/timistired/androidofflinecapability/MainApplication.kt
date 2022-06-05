package com.timistired.androidofflinecapability

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.timistired.androidofflinecapability.data.local.AppleDatabase
import com.timistired.androidofflinecapability.data.local.AppleLocalDataSource

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        val database = Room.databaseBuilder(
            applicationContext,
            AppleDatabase::class.java,
            "DB_NAME"
        ).build()

        AppleLocalDataSource.setDatabase(database)
    }

    companion object {
        private lateinit var appContext: Context
        fun getAppContext() = appContext
    }
}