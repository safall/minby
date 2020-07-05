package com.afi.minby.repository

import com.afi.minby.home.HomeMenuItem
import com.afi.minby.model.User
import com.afi.minby.other.LocalData
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val remoteService: RemoteService) {

    fun login(username: String, password: String): Single<User> {
        return remoteService.login(username, password)
    }

    fun signup(username: String, password: String): Single<User> {
        return remoteService.signup(username, password)
    }

    fun getHomeMenuItems(): Single<List<HomeMenuItem>> {
        return Single.just(LocalData.getDummyData())
    }
}
