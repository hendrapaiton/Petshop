package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.data.remote.dto.AuthService
import cloud.hendra.petshop.data.repository.AuthRepository
import cloud.hendra.petshop.data.repository.AuthRepositoryImpl
import cloud.hendra.petshop.domain.usecase.AuthUseCase
import cloud.hendra.petshop.ui.viewmodel.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    single<AuthService> { get<Retrofit>().create(AuthService::class.java) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { AuthUseCase(get()) }
    viewModel { AuthViewModel(get()) }
}