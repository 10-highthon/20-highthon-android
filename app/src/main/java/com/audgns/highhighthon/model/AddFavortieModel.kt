package com.audgns.highhighthon.model

import com.google.gson.annotations.SerializedName

data class AddFavoriteModel(
    @SerializedName("channelId") val channelId: String
)
