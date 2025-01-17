package com.soul.taskbreeze.pre_auth.pre_auth_loading.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.taskbreeze.core.util.Resource
import com.soul.taskbreeze.pre_auth.pre_auth_loading.domain.use_case.GetPreAuthDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreAuthViewmodel @Inject constructor(
    private val getPreAuthDetails: GetPreAuthDetails,
) : ViewModel() {

    private val _state = mutableStateOf(PreAuthState())
    val state: State<PreAuthState> = _state

    init {
        getPreAuthData()
    }


    private fun getPreAuthData() {
        var accumulatedProgress = 0f // To accumulate progress


        CoroutineScope(Dispatchers.IO).launch {
            getPreAuthDetails.execute().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        accumulatedProgress += result.data
                            ?: 0f // Add the progress value to the accumulated progress
                        _state.value =
                            PreAuthState(data = accumulatedProgress) // Update the state with the accumulated progress
                    }

                    is Resource.Error -> {
                        _state.value = PreAuthState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = PreAuthState(isLoading = true)
                    }
                }
            }
        }

        viewModelScope.launch {
            getPreAuthDetails.execute()
        }
    }

}