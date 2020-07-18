package com.afi.minby.home.homemenu

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afi.minby.repository.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel @ViewModelInject constructor(
    private val repository: RemoteRepository
) : ViewModel() {

    val isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val showError: MutableLiveData<String> = MutableLiveData()
    val gethomeMenuItem: MutableLiveData<List<HomeMenuItem>> = MutableLiveData()
    val gethomeMenuItem_: MutableLiveData<List<HomeMenuItem>>
        get() = gethomeMenuItem
    private val disposable = CompositeDisposable()

    fun addHomeMenuItems() {
        disposable.add(
            repository.getHomeMenuItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ menuItems ->
                    gethomeMenuItem.postValue(menuItems)
                }) {
                    showError.postValue(it.message)
                    gethomeMenuItem.postValue(emptyList())
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
