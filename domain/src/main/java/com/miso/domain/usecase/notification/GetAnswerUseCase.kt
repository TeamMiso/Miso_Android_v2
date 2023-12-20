package com.miso.domain.usecase.notification

import com.miso.domain.repository.NotificationRepository
import javax.inject.Inject

class GetAnswerUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        notificationRepository.getAnswer(id = id)
    }
}