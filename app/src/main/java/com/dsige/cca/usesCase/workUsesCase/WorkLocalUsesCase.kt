package com.dsige.cca.usesCase.workUsesCase

import com.dsige.cca.data.model.RegisterWork
import com.dsige.cca.data.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkLocalUsesCase @Inject constructor(private val localRepository: LocalRepository) {

    operator fun invoke(): Flow<List<RegisterWork>> = localRepository.getRegisterWorkes()

}