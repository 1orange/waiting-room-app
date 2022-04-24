package com.example.waitingroom.di

import com.example.waitingroom.data.api.WaitingRoomApi
import com.example.waitingroom.data.repository.WaitingRoomRepository
import com.example.waitingroom.domain.repository.WaitingRoomRepositoryInterface
import com.example.waitingroom.utils.Constants
import com.example.waitingroom.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWaitingRoomApi(): WaitingRoomApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WaitingRoomApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWaitingRoomRepository(api: WaitingRoomApi): WaitingRoomRepositoryInterface {
        return WaitingRoomRepository(api)
    }
}