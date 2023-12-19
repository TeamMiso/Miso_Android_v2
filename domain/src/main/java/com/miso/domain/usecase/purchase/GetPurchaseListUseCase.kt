package com.miso.domain.usecase.purchase

import com.miso.domain.repository.PurchaseRepository
import javax.inject.Inject

class GetPurchaseListUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        purchaseRepository.getPurchaseList()
    }
}