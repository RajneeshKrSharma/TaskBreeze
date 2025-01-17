package com.soul.taskbreeze.auth.login.domain.use_case

import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpRequestDto
import com.soul.taskbreeze.auth.login.data.remote.dto.GetOtpResponseDto
import com.soul.taskbreeze.auth.login.data.repository.LoginRepository
import com.soul.taskbreeze.core.ApiUseCase
import com.soul.taskbreeze.core.util.Resource
import javax.inject.Inject

class RequestOtpUseCase @Inject constructor(
    private val repository: LoginRepository,
) : ApiUseCase<Resource<GetOtpResponseDto>, GetOtpRequestDto> {  // String is the argument type

    override suspend fun execute(args: GetOtpRequestDto?): Resource<GetOtpResponseDto> {
        return if (args != null) {
            Resource.Success(repository.requestOtp(args))
        } else {
            Resource.Error("Mobile number is required")
        }
    }
}

