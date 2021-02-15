package com.a4nt0n64r.cahetest.data.repository

import com.a4nt0n64r.cahetest.domain.model.CloudPlayer
import com.a4nt0n64r.cahetest.network.ApiService
import com.a4nt0n64r.cahetest.network.NetworkRepository
import java.io.IOException

class NetworkRepoImpl(private val apiService: ApiService) : NetworkRepository {

    @Throws(IOException::class, RuntimeException::class)
    override suspend fun getPlayer(): CloudPlayer? {
        val call = apiService.getPlayerFromCloud()
        return call.execute().body()
    }
}