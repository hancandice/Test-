package com.a4nt0n64r.cahetest.network

import com.a4nt0n64r.cahetest.domain.model.CloudPlayer

interface NetworkRepository {

    suspend fun getPlayer(): CloudPlayer?

}