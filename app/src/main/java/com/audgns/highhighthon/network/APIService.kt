package com.audgns.highhighthon.network

import com.audgns.highhighthon.model.AddFavoriteModel
import com.audgns.highhighthon.model.ChannelResponse
import com.audgns.highhighthon.model.DeleteFavoriteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface APIService {

    @GET("/favorite/all")
    suspend fun getFavorite(): ChannelResponse

    @POST("/favorite/remove")
    suspend fun deleteFavorite(
        @Body body: DeleteFavoriteModel
    )

    @PUT("/favorite/add")
    suspend fun addFavorite(
        @Body body: AddFavoriteModel
    )
}