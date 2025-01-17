package com.soul.taskbreeze.core.network

object Api {
    const val BASE_URL = "https://taskbreezeapi.pythonanywhere.com"
    const val PRE_APP_DETAILS = "/api/pre/app-details"

    // LOGIN APIS
    const val GET_OTP = "/api/login/get-otp"
    const val LOGIN_VIA_OTP = "/api/login/login-via-otp"

    //SPLIT EXPENSE APIS
    const val GROUP_EXPENSE = "api/list/group-expense"

}