package com.polinasmogi.isskicker.api

import com.polinasmogi.isskicker.api.models.MembersData
import retrofit2.Call
import retrofit2.Response

class Requests {

    fun getMembers(
        apiClient: Services,
        callback: (MembersData) -> Unit,
        errorHandler: ((errorBody: String) -> Unit)?) {

        apiClient.getMembers().enqueue(object: retrofit2.Callback<MembersData> {
            override fun onResponse(
                call: Call<MembersData>,
                response: Response<MembersData>) {
                    response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<MembersData>, t: Throwable) {

            }

        })

    }

}