package com.afi.minby.auth.launcher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class LauncherViewModel : ViewModel() {

    var loadHomePage = MutableLiveData<Unit>()

    private var disposable: Disposable? = null

    fun start() {
        disposable?.dispose()
        disposable = Observable.timer(2, TimeUnit.SECONDS)
            .subscribe { loadHomePage.postValue(Unit) }
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}
