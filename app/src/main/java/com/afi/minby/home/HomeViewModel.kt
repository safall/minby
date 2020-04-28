package com.afi.minby.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afi.minby.repository.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: RemoteRepository
) : ViewModel() {

    val isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val showError: MutableLiveData<String> = MutableLiveData()
    val homeMenuItems: MutableLiveData<List<HomeMenuItem>> = MutableLiveData()
    val homeMenuItem_: MutableLiveData<List<HomeMenuItem>>
    get() = homeMenuItems
    private val disposable = CompositeDisposable()

    fun addHomeMenuItems() {
        disposable.add(
            repository.home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    if (result.isEmpty()) {
                        isEmpty.postValue(true)
                    } else {
                        homeMenuItems.postValue(getDummyData())
                    }
                }) {
                    showError.postValue(it.message)
                    homeMenuItems.postValue(getDummyData())
                }
        )
    }

    private fun getDummyData(): List<HomeMenuItem> {
        return arrayListOf<HomeMenuItem>()
            .apply {
                add(
                    0,
                    HomeMenuItem(
                        "Send oss en ide",
                        "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                        "idea"
                    )
                )
                add(
                    1,
                    HomeMenuItem(
                        "Send oss en vurdering",
                        "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                        "vurdering"
                    )
                )
                add(
                    2,
                    HomeMenuItem(
                        "Send oss en intervju",
                        "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                        "intervju"
                    )
                )
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
