package com.soul.taskbreeze.core.di

import com.soul.taskbreeze.auth.login.data.repository.LoginRepository
import com.soul.taskbreeze.auth.login.domain.repository.LoginRepositoryImpl
import com.soul.taskbreeze.post_auth.split_expense.data.repository.SplitExpenseRepositoryImpl
import com.soul.taskbreeze.post_auth.split_expense.domain.repository.SplitExpenseRepository
import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.repository.PreAuthRepositoryImpl
import com.soul.taskbreeze.pre_auth.pre_auth_loading.domain.repository.PreAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPreAuthRepository(
        preRepositoryImpl: PreAuthRepositoryImpl
    ): PreAuthRepository

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindSplitExpenseRepository(
        splitExpenseRepository: SplitExpenseRepositoryImpl
    ): SplitExpenseRepository


}