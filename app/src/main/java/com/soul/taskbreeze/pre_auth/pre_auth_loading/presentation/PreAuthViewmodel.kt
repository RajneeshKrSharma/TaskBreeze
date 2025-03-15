package com.soul.taskbreeze.pre_auth.pre_auth_loading.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.taskbreeze.core.util.ApiResponseResource
import com.soul.taskbreeze.core.util.Resource
import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.remote.dto.PreAuthInfoDto
import com.soul.taskbreeze.pre_auth.pre_auth_loading.domain.use_case.GetPreAuthDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreAuthViewmodel @Inject constructor(
    private val getPreAuthDetails: GetPreAuthDetails,
) : ViewModel() {

    private val _preAuthInfoState = mutableStateOf<Resource<PreAuthInfoDto>>(Resource.Default())
    val preAuthInfoState: State<Resource<PreAuthInfoDto>> = _preAuthInfoState

    init {
        getPreAuthData()
    }


    private fun getPreAuthData() {
        _preAuthInfoState.value = Resource.Loading()
        viewModelScope.launch {
            when(val result = getPreAuthDetails.execute()) {
                is ApiResponseResource.Error -> _preAuthInfoState.value = Resource.Error(result.errorMessage)
                is ApiResponseResource.Success -> _preAuthInfoState.value = Resource.Success(result.data)
            }
        }
    }

}