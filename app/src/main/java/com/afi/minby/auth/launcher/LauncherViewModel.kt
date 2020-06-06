package com.afi.minby.auth.launcher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class LauncherViewModel : ViewModel() {

    var loadHomePage = MutableLiveData<Boolean>()

    private lateinit var disposable: Disposable

    fun delay() {
        disposable = Observable.timer(2, TimeUnit.SECONDS)
            .subscribe {
                loadHomePage.postValue(true)
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
