package com.a4nt0n64r.cahetest.network

import com.a4nt0n64r.cahetest.domain.model.CloudPlayer
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("player.txt")
    fun getPlayerFromCloud(): Call<CloudPlayer>

}