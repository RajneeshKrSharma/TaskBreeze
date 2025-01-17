package com.soul.taskbreeze.pre_auth.pre_auth_loading.domain.use_case

import com.soul.taskbreeze.core.ApiUseCase
import com.soul.taskbreeze.core.PendingRequestManager
import com.soul.taskbreeze.core.util.Resource
import com.soul.taskbreeze.pre_auth.pre_auth_loading.domain.repository.PreAuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetPreAuthDetails @Inject constructor(
    private val repository: PreAuthRepository,
    private val pendingRequestManager: PendingRequestManager
) : ApiUseCase<Flow<Resource<Float>>, Unit> {

    override suspend fun execute(args: Unit?): Flow<Resource<Float>> = flow {

        try {
            delay(200)
            emit(Resource.Loading())
            with(repository.getPreAuthData()) {
                appSpecificDetails?.let {
                    emit(Resource.Success(0.4f))
                } ?: emit(Resource.Error("Couldn't reach server. Check your internet connection."))

                delay(500)

                appTourInfo?.let {
                    // TODO() -- Use this way to save data in local db
                    /*appTourInfoData.filterNotNull().let { appTourInfo ->
                        db.dao.deleteAppTourData()
                        db.dao.insertAppTourData(
                            appTourInfo.map {
                                AppTourEntity(
                                    image = it.image,
                                    subtitle = it.subtitle,
                                    title = it.title
                                )
                            }
                        )
                    }*/
                    emit(Resource.Success(0.4f))
                } ?: emit(Resource.Error("Couldn't reach server. Check your internet connection."))

                delay(500)

                appUpdateInfo?.let {
                    emit(Resource.Success(0.2f))
                } ?: emit(Resource.Error("Couldn't reach server. Check your internet connection."))

                pendingRequestManager.addRequest(isAutoRetry = true, request = this@GetPreAuthDetails)
            }

        }   catch(e: IOException) {
            emit(Resource.Error(e.message ?: "NA"))
        }
    }
}


/*
class GetPreAuthDetails @Inject constructor(
    private val repository: PreAuthRepository,
    private val db: TaskBreezeDatabase,
) {
    operator fun invoke(): Flow<Resource<Float>> = flow {
        try {
            emit(Resource.Loading())
            Log.d("Rajneesh", "invoke: ")
            val rqst = repository.callRequestOtp(LoginRequest("+919876543210"))
            Log.i("Rajneesh", "- - - > $rqst")

            with(repository.getPreAuthData()) {
                appSpecificDetails?.let {
                    emit(Resource.Success(0.4f))
                } ?: emit(Resource.Error("Couldn't reach server. Check your internet connection."))

                delay(500)

                appTourInfo?.let { appTourInfoData ->
                    appTourInfoData.filterNotNull().let { appTourInfo ->
                        db.dao.deleteAppTourData()
                        db.dao.insertAppTourData(
                            appTourInfo.map {
                                AppTourEntity(
                                    image = it.image,
                                    subtitle = it.subtitle,
                                    title = it.title
                                )
                            }
                        )
                    }
                    emit(Resource.Success(0.4f))
                } ?: emit(Resource.Error("Couldn't reach server. Check your internet connection."))

                delay(500)

                appUpdateInfo?.let {
                    emit(Resource.Success(0.2f))
                } ?: emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }

        }   catch(e: IOException) {
            emit(Resource.Error(e.message ?: "NA"))
        }
    }
}*/

/*class GetPreAuthDetails @Inject constructor(
    private val repository: PreAuthRepository,
    private val db: TaskBreezeDatabase,
    private val pendingRequestManager: PendingRequestManager
) : ApiUseCase<MutableStateFlow<Resource<Float>>> {

    val preAuthFlow = MutableStateFlow<Resource<Float>>(Resource.Loading())


    override suspend fun execute(): MutableStateFlow<Resource<Float>> {
        Log.d("Rajneesh", "invoke::::::::::::::::::::::::::::::::::::::::::::::::::::::: ")

        try {

            preAuthFlow.emit(Resource.Loading())  // Emit loading state

            with(repository.getPreAuthData()) {
                appSpecificDetails?.let {
                    preAuthFlow.emit(Resource.Success(0.4f))

                } ?: preAuthFlow.emit(Resource.Error("Couldn't reach server. Check your internet connection."))

                delay(500)

                appTourInfo?.let { appTourInfoData ->
                    *//*appTourInfoData.filterNotNull().let { appTourInfo ->
                        db.dao.deleteAppTourData()
                        db.dao.insertAppTourData(
                            appTourInfo.map {
                                AppTourEntity(
                                    image = it.image,
                                    subtitle = it.subtitle,
                                    title = it.title
                                )
                            }
                        )
                    }*//*
                    preAuthFlow.emit(Resource.Success(0.4f))  // Emit success after app tour data
                } ?: preAuthFlow.emit(Resource.Error("Couldn't reach server. Check your internet connection."))

                delay(500)

                appUpdateInfo?.let {
                    preAuthFlow.emit(Resource.Success(0.2f))  // Emit success after app update info
                } ?: preAuthFlow.emit(Resource.Error("Couldn't reach server. Check your internet connection."))

                pendingRequestManager.addRequest(isAutoRetry = true, request = this@GetPreAuthDetails)
            }

        } catch (e: IOException) {
            preAuthFlow.emit(Resource.Error(e.message ?: "NA"))  // Emit error in case of IOException
        }

        // Return the SharedFlow
        return preAuthFlow
    }
}*/

