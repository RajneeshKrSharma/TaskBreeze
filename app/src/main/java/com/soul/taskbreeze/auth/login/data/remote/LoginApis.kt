package com.soul.taskbreeze.auth.login.data.remote

import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpResponseDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpResponseDto
import com.soul.taskbreeze.core.network.Api.GET_OTP
import com.soul.taskbreeze.core.network.Api.LOGIN_VIA_OTP
import com.soul.taskbreeze.core.network.BaseApi

import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApis: BaseApi {
    @POST(GET_OTP)
    suspend fun getOtpResponse(
        @Body getOtpRequestDto: GetOtpRequestDto
    ): GetOtpResponseDto

    @POST(LOGIN_VIA_OTP)
    suspend fun getLoginViaOtpResponse(
        @Body loginRequest: LoginViaOtpRequestDto
    ): LoginViaOtpResponseDto
}