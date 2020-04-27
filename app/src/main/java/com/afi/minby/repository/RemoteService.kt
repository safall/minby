package com.afi.minby.repository

import com.afi.minby.model.Register
import com.afi.minby.model.User
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RemoteService {

    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email") username: String, @Field("password") password: String): Single<User>

    @POST("signup")
    @FormUrlEncoded
    fun signup(@Field("email")username: String, @Field("password") password: String): Single<Register>
}
