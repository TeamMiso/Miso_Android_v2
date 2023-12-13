package com.miso.miso_android_v2.module

import com.miso.data.remote.datasource.auth.AuthDataSource
import com.miso.data.remote.datasource.auth.AuthDataSourceImpl
import com.miso.data.remote.datasource.email.EmailDataSource
import com.miso.data.remote.datasource.email.EmailDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDatasourceModule {
    @Binds
    abstract fun provideAuthDataSource(
        authDatasourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    abstract fun provideEmailDataSource(
        emailDataSourceImpl: EmailDataSourceImpl
    ): EmailDataSource
}