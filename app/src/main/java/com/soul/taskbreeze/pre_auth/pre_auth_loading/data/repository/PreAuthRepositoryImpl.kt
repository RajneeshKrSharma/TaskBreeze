package com.soul.taskbreeze.pre_auth.pre_auth_loading.data.repository

import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.remote.PreAuthApis
import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.remote.dto.PreAuthInfoDto
import com.soul.taskbreeze.pre_auth.pre_auth_loading.domain.repository.PreAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreAuthRepositoryImpl @Inject constructor(
    private val api: PreAuthApis,
): PreAuthRepository {
    override suspend fun getPreAuthData(): PreAuthInfoDto {
        return api.getPreAppDetails()
    }
}