package com.dsige.cca.usesCase.syncUsesCase

import com.dsige.cca.data.model.Sync
import com.dsige.cca.data.repository.RestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SyncRestUsesCase @Inject constructor(private val restRepository: RestRepository) {

    suspend operator fun invoke(): Flow<Sync> = restRepository.getSync()
}