package com.unique.tba.post_auth.split_expense.domain.repository

import com.unique.tba.post_auth.split_expense.data.remote.dto.GroupExpenseResponseDto

interface SplitExpenseRepository {
    suspend fun getGroupExpense(): GroupExpenseResponseDto
}