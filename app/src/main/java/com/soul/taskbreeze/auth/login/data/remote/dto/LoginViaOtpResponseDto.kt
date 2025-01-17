package com.soul.taskbreeze.auth.login.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LoginViaOtpResponseDto(
    @SerializedName("data")
    val `data`: Data?
) {
    data class Data(
        @SerializedName("authData")
        val authData: AuthData?,
        @SerializedName("mobileNo")
        val mobileNo: String?
    ) {
        data class AuthData(
            @SerializedName("key")
            val key: String?,
            @SerializedName("user")
            val user: Int?
        )
    }

    companion object {
        val empty = LoginViaOtpResponseDto(null)
    }
}