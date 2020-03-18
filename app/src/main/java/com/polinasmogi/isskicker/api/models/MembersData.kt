package com.polinasmogi.isskicker.api.models

import com.google.gson.annotations.SerializedName

data class MembersData (

    @SerializedName("data")
    val data : ArrayList<Player>

)