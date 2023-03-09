package com.dsige.cca.usesCase.checkSessionUsesCase

import com.dsige.cca.data.model.Usuario
import com.dsige.cca.data.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckSessionUsesCase @Inject constructor(private val localRepository: LocalRepository) {

    operator fun invoke(): Flow<Usuario?> = localRepository.getUsuario()

}