package com.polinasmogi.isskicker.api

import com.polinasmogi.isskicker.api.models.Player
import retrofit2.Call
import retrofit2.Response

class Requests {

    fun getMembers(
        apiClient: Services,
        callback: (ArrayList<Player>) -> Unit,
        errorHandler: ((errorBody: String) -> Unit)?) {

        apiClient.getMembers().enqueue(object: retrofit2.Callback<ArrayList<Player>> {
            override fun onResponse(
                call: Call<ArrayList<Player>>,
                response: Response<ArrayList<Player>>) {
                    response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<ArrayList<Player>>, t: Throwable) {

            }

        })

    }

}