package com.soul.taskbreeze.auth.split_expense.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.taskbreeze.auth.split_expense.data.remote.dto.GroupExpenseResponseDto
import com.soul.taskbreeze.auth.split_expense.domain.use_case.GetGroupExpenseUseCase
import com.soul.taskbreeze.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplitExpenseViewModel @Inject constructor(
    getGroupExpenseUseCase: GetGroupExpenseUseCase
) : ViewModel() {
    private val _groupExpenseState = mutableStateOf(GroupExpenseResponseDto.empty())
    val groupExpenseState: State<GroupExpenseResponseDto> = _groupExpenseState

    init {
        viewModelScope.launch {
            with(getGroupExpenseUseCase.execute()) {
                when (this) {
                    is Resource.Success -> {
                        _groupExpenseState.value = this.data ?: GroupExpenseResponseDto.empty()
                    }

                    is Resource.Error -> TODO()
                    is Resource.Loading -> TODO()
                }
            }
        }
    }
}

