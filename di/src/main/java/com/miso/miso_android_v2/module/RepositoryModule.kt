package com.miso.miso_android_v2.module

import com.miso.data.repository.AuthRepositoryImpl
import com.miso.data.repository.EmailRepositoryImpl
import com.miso.domain.repository.AuthRepository
import com.miso.domain.repository.EmailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun provideEmailRepository(
        emailRepositoryImpl: EmailRepositoryImpl
    ): EmailRepository
}