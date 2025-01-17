package com.soul.taskbreeze.auth.login.domain.repository

import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpResponseDto
import com.soul.taskbreeze.auth.login.data.remote.LoginApis
import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpResponseDto
import com.soul.taskbreeze.auth.login.data.repository.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val api: LoginApis,
) : LoginRepository {
    override suspend fun requestOtp(getOtpRequest: GetOtpRequestDto): GetOtpResponseDto {
        return api.getOtpResponse(getOtpRequest)
    }

    override suspend fun loginViaOtp(loginRequest: LoginViaOtpRequestDto): LoginViaOtpResponseDto {
        return api.getLoginViaOtpResponse(loginRequest)
    }
}