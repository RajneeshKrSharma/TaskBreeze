package com.soul.taskbreeze.pre_auth.pre_auth_loading.domain.repository

import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.remote.dto.PreAuthInfoDto

interface PreAuthRepository {
    suspend fun getPreAuthData(): PreAuthInfoDto
}