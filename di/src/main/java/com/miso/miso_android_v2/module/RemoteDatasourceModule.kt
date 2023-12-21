package com.miso.miso_android_v2.module

import com.miso.data.remote.datasource.auth.AuthDataSource
import com.miso.data.remote.datasource.auth.AuthDataSourceImpl
import com.miso.data.remote.datasource.email.EmailDataSource
import com.miso.data.remote.datasource.email.EmailDataSourceImpl
import com.miso.data.remote.datasource.environment.EnvironmentDataSource
import com.miso.data.remote.datasource.environment.EnvironmentDataSourceImpl
import com.miso.data.remote.datasource.inquiry.InquiryDataSource
import com.miso.data.remote.datasource.inquiry.InquiryDataSourceImpl
import com.miso.data.remote.datasource.notification.NotificationDataSource
import com.miso.data.remote.datasource.notification.NotificationDataSourceImpl
import com.miso.data.remote.datasource.purchase.PurchaseDataSource
import com.miso.data.remote.datasource.purchase.PurchaseDataSourceImpl
import com.miso.data.remote.datasource.recyclables.RecyclablesDataSource
import com.miso.data.remote.datasource.recyclables.RecyclablesDataSourceImpl
import com.miso.data.remote.datasource.shop.ShopDataSource
import com.miso.data.remote.datasource.shop.ShopDataSourceImpl
import com.miso.data.remote.datasource.user.UserDataSource
import com.miso.data.remote.datasource.user.UserDataSourceImpl
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

    @Binds
    abstract fun provideInquiryDataSource(
        inquiryDataSourceImpl: InquiryDataSourceImpl
    ): InquiryDataSource

    @Binds
    abstract fun provideRecyclablesDataSource(
        recyclablesDataSourceImpl: RecyclablesDataSourceImpl
    ): RecyclablesDataSource

    @Binds
    abstract fun provideShopDataSource(
        shopDataSourceImpl: ShopDataSourceImpl
    ): ShopDataSource

    @Binds
    abstract fun provideUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource

    @Binds
    abstract fun providePurchaseDataSource(
        purchaseDataSourceImpl: PurchaseDataSourceImpl
    ): PurchaseDataSource

    @Binds
    abstract fun provideNotificationDataSource(
        notificationDataSourceImpl: NotificationDataSourceImpl
    ): NotificationDataSource

    @Binds
    abstract fun provideEnvironmentDataSource(
        environmentDataSourceImpl: EnvironmentDataSourceImpl
    ): EnvironmentDataSource
}