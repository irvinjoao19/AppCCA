package com.dsige.cca.usesCase.loginUsesCase

import com.dsige.cca.data.model.Usuario
import com.dsige.cca.data.repository.LocalRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginLocalUsesCase @Inject constructor(private val localRepository: LocalRepository) {

    suspend operator fun invoke(u: Usuario) : Boolean = localRepository.insertUsuario(u)

}