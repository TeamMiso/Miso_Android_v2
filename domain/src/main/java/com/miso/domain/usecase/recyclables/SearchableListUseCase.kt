package com.miso.domain.usecase.recyclables

import com.miso.domain.repository.RecyclablesRepository
import javax.inject.Inject

class SearchableListUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        recyclablesRepository.searchableList()
    }
}