package com.soul.taskbreeze.post_auth.split_expense.data.remote.dto

import com.soul.taskbreeze.core.network.Api.GROUP_EXPENSE
import com.soul.taskbreeze.core.network.BaseApi
import retrofit2.http.GET

interface SplitExpenseApis: BaseApi {
    @GET(GROUP_EXPENSE)
    suspend fun getGroupExpense(): GroupExpenseResponseDto
}