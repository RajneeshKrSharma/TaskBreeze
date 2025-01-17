package com.soul.taskbreeze.auth.login.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LoginViaOtpRequestDto(
    @SerializedName("mobileNo")
    val mobileNo: String?,
    @SerializedName("otp")
    val otp: String?,
    @SerializedName("fcmToken")
    val fcmToken: String?
)