package com.afi.minby.repository

import com.afi.minby.model.User
import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RemoteService {

    @POST("login")
    @FormUrlEncoded
    fun login(username: String, password: String): Single<User>
}
