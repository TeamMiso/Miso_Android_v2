package com.miso.miso_android_v2.module

import com.miso.data.repository.AuthRepositoryImpl
import com.miso.data.repository.EmailRepositoryImpl
import com.miso.data.repository.EnvironmentRepositoryImpl
import com.miso.data.repository.InquiryRepositoryImpl
import com.miso.data.repository.NotificationRepositoryImpl
import com.miso.data.repository.PurchaseRepositoryImpl
import com.miso.data.repository.RecyclablesRepositoryImpl
import com.miso.data.repository.ShopRepositoryImpl
import com.miso.data.repository.UserRepositoryImpl
import com.miso.domain.repository.AuthRepository
import com.miso.domain.repository.EmailRepository
import com.miso.domain.repository.EnvironmentRepository
import com.miso.domain.repository.InquiryRepository
import com.miso.domain.repository.NotificationRepository
import com.miso.domain.repository.PurchaseRepository
import com.miso.domain.repository.RecyclablesRepository
import com.miso.domain.repository.ShopRepository
import com.miso.domain.repository.UserRepository
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

    @Binds
    abstract fun provideInquiryRepository(
        inquiryRepositoryImpl: InquiryRepositoryImpl
    ): InquiryRepository

    @Binds
    abstract fun provideRecyclablesRepository(
        recyclablesRepositoryImpl: RecyclablesRepositoryImpl
    ): RecyclablesRepository

    @Binds
    abstract fun provideShopRepository(
        shopRepositoryImpl: ShopRepositoryImpl
    ): ShopRepository

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun providePurchaseRepository(
        purchaseRepositoryImpl: PurchaseRepositoryImpl
    ): PurchaseRepository

    @Binds
    abstract fun provideNotificationRepository(
        notificationRepositoryImpl: NotificationRepositoryImpl
    ): NotificationRepository

    @Binds
    abstract fun provideEnvironmentRepository(
        environmentRepositoryImpl: EnvironmentRepositoryImpl
    ): EnvironmentRepository
}