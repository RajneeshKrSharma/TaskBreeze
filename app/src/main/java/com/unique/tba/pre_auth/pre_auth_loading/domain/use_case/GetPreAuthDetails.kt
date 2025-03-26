package com.unique.tba.pre_auth.pre_auth_loading.domain.use_case

import com.unique.tba.core.ApiUseCase
import com.unique.tba.core.PendingRequestManager
import com.unique.tba.core.util.ApiResponseResource
import com.unique.tba.pre_auth.pre_auth_loading.data.remote.dto.PreAuthInfoDto
import com.unique.tba.pre_auth.pre_auth_loading.domain.repository.PreAuthRepository
import javax.inject.Inject

class GetPreAuthDetails @Inject constructor(
    private val repository: PreAuthRepository,
    private val pendingRequestManager: PendingRequestManager
) : ApiUseCase<ApiResponseResource<PreAuthInfoDto>, Unit> {

    override suspend fun execute(args: Unit?): ApiResponseResource<PreAuthInfoDto> {
        val result = repository.getPreAuthData()
        return if (result.isSuccessful) {
            val resultBody = result.body()
            resultBody?.let { data ->
                ApiResponseResource.Success(data)
            } ?: ApiResponseResource.Error("PreAuthInfo body null")
        } else {
            val errorMsg = result.errorBody()?.string() ?: "Something went wrong (PreAuthInfo error body null)"
            ApiResponseResource.Error(errorMsg)
        }
    }
}