package com.timistired.androidofflinecapability.data.remote

import android.util.Log
import com.timistired.androidofflinecapability.data.model.AppleDTO
import io.reactivex.rxjava3.core.Completable
import java.util.concurrent.TimeUnit

object AppleRemoteDataSource {

    fun uploadApples(apples: List<AppleDTO>): Completable {
        Log.d(this::class.simpleName, "uploading ${apples.size} apples")
        // simulate network request
        return Completable.timer(800, TimeUnit.MILLISECONDS)
            .doOnComplete {
                Log.d(this::class.simpleName, "Done uploading")
            }
    }
}