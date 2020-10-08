package com.afi.minby.home.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afi.minby.model.Feed
import com.afi.minby.repository.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FeedViewModel @ViewModelInject constructor(private val respository: RemoteRepository) :
    ViewModel() {

    private var disposable: Disposable? = null

    var feedItems = MutableLiveData<List<Feed>>()

    fun fetchFeed() {
        disposable = respository.getFeed()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                feedItems.postValue(it)
            }, {
                feedItems.postValue(emptyList())
            })
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}
