package com.unique.tba.post_auth.split_expense.domain.use_case

import com.unique.tba.post_auth.split_expense.data.remote.dto.GroupExpenseResponseDto
import com.unique.tba.post_auth.split_expense.domain.repository.SplitExpenseRepository
import com.unique.tba.core.ApiUseCase
import com.unique.tba.core.util.Resource
import javax.inject.Inject

class GetGroupExpenseUseCase @Inject constructor(
    private val repository: SplitExpenseRepository,
    ):ApiUseCase<Resource<GroupExpenseResponseDto>, Unit> {
    override suspend fun execute(args: Unit?): Resource<GroupExpenseResponseDto> {
        return if(repository.getGroupExpense().data != null) {
            Resource.Success(repository.getGroupExpense())
        } else {
            Resource.Error("ERROR IN FETCHING GROUP RESPONSE")
        }
    }
}