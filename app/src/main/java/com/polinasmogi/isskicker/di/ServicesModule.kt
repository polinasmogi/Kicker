package com.polinasmogi.isskicker.di

import com.polinasmogi.isskicker.api.Requests
import com.polinasmogi.isskicker.api.Services
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import okhttp3.Credentials
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class ServicesModule {

    private val baseUrl = "https://nihao-team.com/api/"

    @Provides
    @Singleton
    fun provideServices(retrofit: Retrofit) : Services {
        return retrofit.create(Services::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofit() : Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            authenticator { _, response ->
                val credential = Credentials.basic("polina", "polina")
                response.request().newBuilder().header("Authorization",
                    credential).build()
            }

        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client.build())
            .addConverterFactory( GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideRequests() : Requests {
        return Requests()
    }


}