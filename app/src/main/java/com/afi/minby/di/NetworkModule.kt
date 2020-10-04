package com.afi.minby.di

import com.afi.minby.model.IdeaTemplateImpl
import com.afi.minby.repository.RemoteRepository
import com.afi.minby.repository.RemoteService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient, gson: Gson): RemoteService {
        return Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RemoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(service: RemoteService): RemoteRepository =
        RemoteRepository(service)

    @Provides
    @Singleton
    fun provideTemplate(): IdeaTemplateImpl = IdeaTemplateImpl()
}
