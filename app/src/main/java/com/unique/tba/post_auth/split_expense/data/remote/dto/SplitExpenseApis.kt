package com.unique.tba.post_auth.split_expense.data.remote.dto

import com.unique.tba.core.network.Api.GROUP_EXPENSE
import com.unique.tba.core.network.BaseApi
import retrofit2.http.GET

interface SplitExpenseApis: BaseApi {
    @GET(GROUP_EXPENSE)
    suspend fun getGroupExpense(): GroupExpenseResponseDto
}