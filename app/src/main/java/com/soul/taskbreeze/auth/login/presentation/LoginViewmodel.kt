package com.soul.taskbreeze.auth.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpResponseDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpResponseDto
import com.soul.taskbreeze.auth.login.domain.use_case.LoginViaOtpUseCase
import com.soul.taskbreeze.auth.login.domain.use_case.RequestOtpUseCase
import com.soul.taskbreeze.core.config.SharedPrefConfig
import com.soul.taskbreeze.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    private val requestOtpUseCase: RequestOtpUseCase,
    private val loginViaOtpUseCase: LoginViaOtpUseCase,
    private val sharedPrefConfig: SharedPrefConfig
) : ViewModel() {

    private val _getOtpState = mutableStateOf(GetOtpResponseDto.empty)
    val getOtpState: State<GetOtpResponseDto> = _getOtpState

    private val _loginViaOtpState = mutableStateOf(LoginViaOtpResponseDto.empty)
    val loginViaOtpState: State<LoginViaOtpResponseDto> = _loginViaOtpState

    fun getOtp(mobileNumber: String) {
        viewModelScope.launch {
            with(requestOtpUseCase.execute(GetOtpRequestDto(mobileNo = mobileNumber))) {
                when (this) {
                    is Resource.Success -> {
                        _getOtpState.value = this.data ?: GetOtpResponseDto.empty
                    }

                    is Resource.Error -> TODO()

                    is Resource.Loading -> TODO()
                    is Resource.Default<*> -> TODO()
                }
            }
        }
    }

    fun loginViaOtp(loginRequest: LoginViaOtpRequestDto) {
        viewModelScope.launch {
            with(loginViaOtpUseCase.execute(loginRequest)) {
                when (this) {
                    is Resource.Success -> {
                        sharedPrefConfig.saveAuthToken(this.data?.data?.authData?.key ?: "")
                        _loginViaOtpState.value = this.data ?: LoginViaOtpResponseDto.empty
                    }

                    is Resource.Error -> TODO()

                    is Resource.Loading -> TODO()
                    is Resource.Default<*> -> TODO()
                }
            }
        }
    }
}