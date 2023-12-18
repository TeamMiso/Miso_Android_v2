package com.miso.domain.usecase.purchase

import com.miso.domain.repository.PurchaseRepository
import javax.inject.Inject

class PurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        purchaseRepository.purchase(id = id)
    }
}