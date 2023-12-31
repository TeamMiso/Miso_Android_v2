package com.miso.miso_android_v2.module

import com.miso.data.remote.api.AuthAPI
import com.miso.data.remote.api.EmailAPI
import com.miso.data.remote.api.EnvironmentAPI
import com.miso.data.remote.api.InquiryAPI
import com.miso.data.remote.api.NotificationAPI
import com.miso.data.remote.api.PurchaseAPI
import com.miso.data.remote.api.RecyclablesAPI
import com.miso.data.remote.api.ShopAPI
import com.miso.data.remote.api.UserAPI
import com.miso.data.util.AuthInterceptor
import com.miso.miso_android_v2.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkhttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(CookieJar.NO_COOKIES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun authService(retrofit: Retrofit): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }

    @Provides
    @Singleton
    fun emailService(retrofit: Retrofit): EmailAPI {
        return retrofit.create(EmailAPI::class.java)
    }

    @Provides
    @Singleton
    fun inquiryService(retrofit: Retrofit): InquiryAPI {
        return retrofit.create(InquiryAPI::class.java)
    }

    @Provides
    @Singleton
    fun recyclablesService(retrofit: Retrofit): RecyclablesAPI {
        return retrofit.create(RecyclablesAPI::class.java)
    }

    @Provides
    @Singleton
    fun shopService(retrofit: Retrofit): ShopAPI {
        return retrofit.create(ShopAPI::class.java)
    }

    @Provides
    @Singleton
    fun userService(retrofit: Retrofit): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }

    @Provides
    @Singleton
    fun purchaseService(retrofit: Retrofit): PurchaseAPI {
        return retrofit.create(PurchaseAPI::class.java)
    }

    @Provides
    @Singleton
    fun notificationService(retrofit: Retrofit): NotificationAPI {
        return retrofit.create(NotificationAPI::class.java)
    }

    @Provides
    @Singleton
    fun environmentService(retrofit: Retrofit): EnvironmentAPI {
        return retrofit.create(EnvironmentAPI::class.java)
    }
 }