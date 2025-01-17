package com.soul.taskbreeze.auth.login.domain.use_case

import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpResponseDto
import com.soul.taskbreeze.auth.login.data.repository.LoginRepository
import com.soul.taskbreeze.core.ApiUseCase
import com.soul.taskbreeze.core.util.Resource
import javax.inject.Inject

class LoginViaOtpUseCase @Inject constructor(
    private val repository: LoginRepository,
) : ApiUseCase<Resource<LoginViaOtpResponseDto>, LoginViaOtpRequestDto> {

    override suspend fun execute(args: LoginViaOtpRequestDto?): Resource<LoginViaOtpResponseDto> {
        return if (args != null) {
            Resource.Success(repository.loginViaOtp(args))
        } else {
            Resource.Error("Mobile number is required")
        }
    }
}

