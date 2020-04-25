package com.afi.minby.repository

import com.afi.minby.model.User
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val remoteService: RemoteService) {

    fun login(username: String, password: String): Single<User> {
        return remoteService.login(username, password)
    }
}
