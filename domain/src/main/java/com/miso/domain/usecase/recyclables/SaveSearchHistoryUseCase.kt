package com.miso.domain.usecase.recyclables

import com.miso.domain.model.recyclables.response.SearchResponseModel
import com.miso.domain.repository.RecyclablesRepository
import javax.inject.Inject

class SaveSearchHistoryUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke(searchHistory: SearchResponseModel) = kotlin.runCatching {
        recyclablesRepository.saveSearchHistory(searchHistory = searchHistory)
    }
}