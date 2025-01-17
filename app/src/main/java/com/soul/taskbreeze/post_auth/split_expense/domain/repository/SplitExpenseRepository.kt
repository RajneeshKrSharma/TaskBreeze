package com.soul.taskbreeze.post_auth.split_expense.domain.repository

import com.soul.taskbreeze.post_auth.split_expense.data.remote.dto.GroupExpenseResponseDto

interface SplitExpenseRepository {
    suspend fun getGroupExpense(): GroupExpenseResponseDto
}