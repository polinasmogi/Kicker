package com.polinasmogi.isskicker.api

import com.polinasmogi.isskicker.api.models.Player
import retrofit2.Call
import retrofit2.http.GET

interface Services {

    @GET("/members")
    fun getMembers(): Call<ArrayList<Player>>

}