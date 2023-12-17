package com.miso.miso_android_v2.module

import com.miso.data.local.datasource.auth.LocalAuthDataSource
import com.miso.data.local.datasource.auth.LocalAuthDataSourceImpl
import com.miso.data.local.datasource.recyclables.LocalRecyclablesDataSource
import com.miso.data.local.datasource.recyclables.LocalRecyclablesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun provideLocalAuthDataSource(
        localAuthDataSourceImpl: LocalAuthDataSourceImpl
    ): LocalAuthDataSource

    @Binds
    abstract fun provideLocalRecyclablesDataSource(
        localRecyclablesDataSourceImpl: LocalRecyclablesDataSourceImpl
    ): LocalRecyclablesDataSource
}