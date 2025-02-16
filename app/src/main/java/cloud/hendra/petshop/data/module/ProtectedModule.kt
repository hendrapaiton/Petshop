package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.data.remote.ProtectedService
import cloud.hendra.petshop.data.repository.ProtectedRepository
import cloud.hendra.petshop.data.repository.ProtectedRepositoryImpl
import cloud.hendra.petshop.ui.viewmodel.ProtectedViewModel
import cloud.hendra.petshop.utils.auth.AuthInterceptor
import cloud.hendra.petshop.utils.auth.SecureTokenManager
import cloud.hendra.petshop.utils.auth.TokenManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val protectedModule = module {
    single<TokenManager> { SecureTokenManager(androidContext()) }
    single { AuthInterceptor(get()) }
    single<ProtectedService> { get<Retrofit>().create(ProtectedService::class.java) }
    single<ProtectedRepository> { ProtectedRepositoryImpl(get()) }
    viewModel { ProtectedViewModel(get(),get()) }
}