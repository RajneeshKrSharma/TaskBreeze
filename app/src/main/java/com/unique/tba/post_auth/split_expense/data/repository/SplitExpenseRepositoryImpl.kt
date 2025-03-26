package com.unique.tba.post_auth.split_expense.data.repository

import com.unique.tba.post_auth.split_expense.data.remote.dto.GroupExpenseResponseDto
import com.unique.tba.post_auth.split_expense.data.remote.dto.SplitExpenseApis
import com.unique.tba.post_auth.split_expense.domain.repository.SplitExpenseRepository
import javax.inject.Inject

class SplitExpenseRepositoryImpl @Inject constructor(
    private val splitExpenseApis: SplitExpenseApis
): SplitExpenseRepository {
    override suspend fun getGroupExpense(): GroupExpenseResponseDto {
        return splitExpenseApis.getGroupExpense()
    }
}