package com.polinasmogi.isskicker.api

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitClientInstance {

    val baseUrl = "https://nihao-team.com/api/"

    fun getRetrofitInstance() : Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            authenticator(object: Authenticator {
                @Throws(IOException::class)
                override fun authenticate(route: Route, response: Response):Request {
                    val credential = Credentials.basic("polina", "polina")
                    return response.request().newBuilder().header("Authorization",
                        credential).build()
                }
            })

        }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

}