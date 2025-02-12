package cloud.hendra.petshop.domain.usecase

import cloud.hendra.petshop.data.repository.AuthRepository

class AuthUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String) = repository.login(username, password)
}