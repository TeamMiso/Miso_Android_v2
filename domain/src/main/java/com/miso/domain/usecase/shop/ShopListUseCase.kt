package com.miso.domain.usecase.shop

import com.miso.domain.repository.ShopRepository
import javax.inject.Inject

class ShopListUseCase @Inject constructor(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        shopRepository.shopList()
    }
}