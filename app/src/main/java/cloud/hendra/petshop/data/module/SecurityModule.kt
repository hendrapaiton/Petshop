package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.utils.auth.SecureTokenManager
import cloud.hendra.petshop.utils.auth.TokenManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val securityModule = module {
    single<TokenManager> {
        SecureTokenManager(
            context = androidContext()
        )
    }
}