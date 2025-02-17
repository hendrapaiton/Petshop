package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.utils.auth.AuthInterceptor
import cloud.hendra.petshop.utils.auth.SecureTokenManager
import cloud.hendra.petshop.utils.auth.TokenManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val protectedModule = module {
    single<TokenManager> { SecureTokenManager(androidContext()) }
    single { AuthInterceptor(get()) }
}