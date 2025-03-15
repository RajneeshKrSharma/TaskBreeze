package com.soul.taskbreeze.pre_auth.pre_auth_loading.domain.repository

import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.remote.dto.PreAuthInfoDto
import retrofit2.Response

interface PreAuthRepository {
    suspend fun getPreAuthData(): Response<PreAuthInfoDto>
}