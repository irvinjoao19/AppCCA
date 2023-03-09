package com.dsige.cca.usesCase.loginUsesCase

import com.dsige.cca.data.model.Usuario
import com.dsige.cca.data.repository.RestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRestUsesCase @Inject constructor(private val restRepository: RestRepository) {

    suspend operator fun invoke(model: MutableMap<String, Any>): Flow<Usuario> =
        restRepository.getLogin(model)
}