package com.soul.taskbreeze.pre_auth.pre_auth_loading.data.remote

import com.soul.taskbreeze.core.network.Api.PRE_APP_DETAILS
import com.soul.taskbreeze.core.network.BaseApi
import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.remote.dto.PreAuthInfoDto
import retrofit2.Response
import retrofit2.http.GET

interface PreAuthApis: BaseApi {
    @GET(PRE_APP_DETAILS)
    suspend fun getPreAppDetails(): Response<PreAuthInfoDto>
}