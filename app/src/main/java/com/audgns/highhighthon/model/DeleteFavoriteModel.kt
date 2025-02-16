package com.audgns.highhighthon.model

import com.google.gson.annotations.SerializedName

data class DeleteFavoriteModel(
    @SerializedName("channelIds") val channelId: List<String>
)