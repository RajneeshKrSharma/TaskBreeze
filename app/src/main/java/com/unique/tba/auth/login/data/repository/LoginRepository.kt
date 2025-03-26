package com.unique.tba.auth.login.data.repository

import com.unique.tba.auth.login.data.remote.dto.ConvertAccessTokenRequestDto
import com.unique.tba.auth.login.data.remote.dto.ConvertAccessTokenResponseDto
import com.unique.tba.auth.login.data.remote.dto.GetOtpRequestDto
import com.unique.tba.auth.login.data.remote.dto.GetOtpResponseDto
import com.unique.tba.auth.login.data.remote.dto.LoginViaOtpRequestDto
import com.unique.tba.auth.login.data.remote.dto.LoginViaOtpResponseDto
import com.unique.tba.auth.login.data.remote.dto.OAuthAccessTokenRequestDto
import com.unique.tba.auth.login.data.remote.dto.OAuthAccessTokenResponseDto
import retrofit2.Response

interface LoginRepository {
    suspend fun requestOtp(getOtpRequest: GetOtpRequestDto): Response<GetOtpResponseDto>

    suspend fun loginViaOtp(loginRequest: LoginViaOtpRequestDto): Response<LoginViaOtpResponseDto>

    suspend fun oAuthAccessToken(oAuthAccessTokenRequestDto: OAuthAccessTokenRequestDto): Response<OAuthAccessTokenResponseDto>

    suspend fun convertAccessToken(convertAccessTokenRequestDto: ConvertAccessTokenRequestDto): Response<ConvertAccessTokenResponseDto>
}