package com.soul.taskbreeze.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.soul.taskbreeze.auth.login.data.remote.LoginApis
import com.soul.taskbreeze.post_auth.split_expense.data.remote.dto.SplitExpenseApis
import com.soul.taskbreeze.core.ConnectivityChecker
import com.soul.taskbreeze.core.PendingRequestManager
import com.soul.taskbreeze.core.config.SharedPrefConfig
import com.soul.taskbreeze.core.local_db.TaskBreezeDatabase
import com.soul.taskbreeze.core.network.Api
import com.soul.taskbreeze.core.network.converter_factory.EncryptedRequestConverterFactory
import com.soul.taskbreeze.core.network.interceptors.ResponseDecryptInterceptors
import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.remote.PreAuthApis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(sharedPrefConfig: SharedPrefConfig): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            //.addConverterFactory(EncryptedRequestConverterFactory(Gson()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val originalRequest = chain.request()
                        val requestWithHeaders = originalRequest.newBuilder()
                            .apply {
                                addHeader("Content-Type", "application/json")
                                addHeader("Accept", "application/json")
                                sharedPrefConfig.getAuthToken()?.let { token ->
                                    if (sharedPrefConfig.isUserViaOtp())
                                        addHeader("Authorization", token)
                                    else
                                        addHeader("Authorization", "Bearer $token")
                                }
                            }
                            .build()
                        chain.proceed(requestWithHeaders)
                    }
                    //.addInterceptor(ResponseDecryptInterceptors())
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
    }


    @Provides
    @Singleton
    fun providePreAuthApi(retrofit: Retrofit): PreAuthApis {
        return retrofit.create(PreAuthApis::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApis {
        return retrofit.create(LoginApis::class.java)
    }

    @Provides
    @Singleton
    fun provideSplitExpenseApi(retrofit: Retrofit): SplitExpenseApis {
        return retrofit.create(SplitExpenseApis::class.java)
    }

    @Provides
    @Singleton
    fun provideTaskBreezeDatabase(@ApplicationContext context: Context): TaskBreezeDatabase {
        return Room.databaseBuilder(
            context,
            TaskBreezeDatabase::class.java,
            "taskBreezeDb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePendingRequestManager(connectivityChecker: ConnectivityChecker): PendingRequestManager {
        return PendingRequestManager(connectivityChecker)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object SharedPreferencesModule {

        @Provides
        @Singleton
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        }
    }

}