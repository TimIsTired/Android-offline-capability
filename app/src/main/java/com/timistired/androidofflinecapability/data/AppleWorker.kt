package com.timistired.androidofflinecapability.data

import android.content.Context
import androidx.work.*
import androidx.work.rxjava3.RxWorker
import com.timistired.androidofflinecapability.MainApplication
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class AppleWorker(context: Context, params: WorkerParameters) : RxWorker(context, params) {

    override fun createWork(): Single<Result> {
        return AppleRepository.uploadPendingApples()
            .andThen(Single.just(Result.success()))
            .onErrorReturnItem(Result.failure())
    }

    override fun getBackgroundScheduler(): Scheduler {
        return Schedulers.io()
    }

    companion object {
        private const val WORK_NAME = "APPLE_WORKER"

        fun enqueue() {
            val workManager = WorkManager.getInstance(MainApplication.getAppContext())
            val workRequest = OneTimeWorkRequestBuilder<AppleWorker>()
                .setConstraints(getWorkerConstraints())
                .build()

            workManager.enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.REPLACE, workRequest)
        }

        private fun getWorkerConstraints(): Constraints {
            // build constraints that require an existing network connection
            return Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        }
    }
}