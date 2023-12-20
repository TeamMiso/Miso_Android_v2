package com.miso.domain.usecase.notification

import com.miso.domain.repository.NotificationRepository
import javax.inject.Inject

class SaveDeviceTokenUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(deviceToken: String) = kotlin.runCatching {
        notificationRepository.saveDeviceToken(deviceToken = deviceToken)
    }
}