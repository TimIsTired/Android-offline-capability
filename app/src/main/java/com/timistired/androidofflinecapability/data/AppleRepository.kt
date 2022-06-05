package com.timistired.androidofflinecapability.data

import android.util.Log
import com.timistired.androidofflinecapability.data.local.AppleLocalDataSource
import com.timistired.androidofflinecapability.data.model.AppleEntity
import com.timistired.androidofflinecapability.data.model.AppleEntity.AppleColor
import com.timistired.androidofflinecapability.data.remote.AppleRemoteDataSource
import io.reactivex.rxjava3.core.Completable
import java.util.*
import kotlin.random.Random

object AppleRepository {

    fun createAndSaveApple(): Completable {
        // create a new entity with randomized values
        val apple = AppleEntity(
            id = UUID.randomUUID(),
            color = AppleColor.values().random(),
            price = Random.nextDouble(from = 0.1, until = 3.0)
        )

        return AppleLocalDataSource.saveApple(apple)
            .doOnComplete {
                Log.d(this::class.simpleName, "New apple created and saved")

                // enqueue worker to upload the data
                AppleWorker.enqueue()
            }
    }

    fun uploadPendingApples(): Completable {
        // fetch apples that are marked as 'not synced'
        return AppleLocalDataSource.getNotSyncedApples()
            .flatMapCompletable { apples ->
                val appleDTOs = apples.map { it.toDTO() }
                // upload the apples
                AppleRemoteDataSource.uploadApples(appleDTOs)
                    // mark the uploaded apples as 'synced'
                    .andThen(markApplesAsSynced(apples))
            }
            // delete apples that are marked as 'synced'
            .andThen(AppleLocalDataSource.deleteSyncedApples())
    }

    private fun markApplesAsSynced(apples: List<AppleEntity>): Completable {
        val updatedApples = apples.map { oldApple ->
            oldApple.copy(synced = true)
        }
        return AppleLocalDataSource.saveApples(updatedApples)
    }
}