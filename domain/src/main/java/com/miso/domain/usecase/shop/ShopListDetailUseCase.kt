package com.miso.domain.usecase.shop

import com.miso.domain.repository.ShopRepository
import javax.inject.Inject

class ShopListDetailUseCase @Inject constructor(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        shopRepository.shopListDetail(id = id)
    }
}