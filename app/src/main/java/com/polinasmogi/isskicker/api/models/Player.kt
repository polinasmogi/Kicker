package com.polinasmogi.isskicker.api.models

import com.google.gson.annotations.SerializedName

data class Player (

    @SerializedName("id")
    val id : Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("photo")
    val photo : Photos

)

data class Photos (

    @SerializedName("small")
    val small : String

)