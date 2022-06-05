package com.timistired.androidofflinecapability.ui

import androidx.lifecycle.ViewModel
import com.timistired.androidofflinecapability.data.AppleRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun createData() {
        disposables.add(
            AppleRepository.createAndSaveApple()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error ->
                    error.printStackTrace()
                })
        )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}