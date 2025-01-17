package com.soul.taskbreeze.auth.login.data.repository

import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpResponseDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpResponseDto

interface LoginRepository {
    suspend fun requestOtp(getOtpRequest: GetOtpRequestDto): GetOtpResponseDto

    suspend fun loginViaOtp(loginRequest: LoginViaOtpRequestDto): LoginViaOtpResponseDto
}