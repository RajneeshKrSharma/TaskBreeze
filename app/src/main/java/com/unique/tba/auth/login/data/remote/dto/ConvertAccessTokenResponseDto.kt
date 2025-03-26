package com.unique.tba.auth.login.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ConvertAccessTokenResponseDto(
    @SerializedName("access_token")
    val accessToken: String?,
)