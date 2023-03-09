package com.dsige.cca.usesCase.syncUsesCase

import com.dsige.cca.data.model.Sync
import com.dsige.cca.data.repository.LocalRepository
import javax.inject.Inject

class SyncLocalUsesCase @Inject constructor(private val localRepository: LocalRepository) {

    suspend operator fun invoke() : Boolean = localRepository.deleteAll()

    suspend operator fun invoke(s: Sync) : Boolean = localRepository.saveSync(s)

}