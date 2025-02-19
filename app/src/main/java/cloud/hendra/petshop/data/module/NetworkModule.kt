package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.data.remote.IndexService
import cloud.hendra.petshop.data.remote.ProtectedService
import cloud.hendra.petshop.data.remote.RefreshService
import cloud.hendra.petshop.data.remote.SaldoService
import cloud.hendra.petshop.utils.Constant.Companion.BASE_URL
import cloud.hendra.petshop.utils.auth.AuthInterceptor
import cloud.hendra.petshop.utils.auth.ServerCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { ServerCookieJar(get()) }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .cookieJar(ServerCookieJar(get()))
            .addInterceptor(
                AuthInterceptor(
                    tokenManager = get(),
                    excludedPaths = setOf(
                        "/api/v1/",
                        "/api/v1/token/",
                        "/api/v1/token/refresh/"
                    )
                )
            )
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(IndexService::class.java) }
    single<ProtectedService> { get<Retrofit>().create(ProtectedService::class.java) }
    single<RefreshService> { get<Retrofit>().create(RefreshService::class.java) }
    single<SaldoService> { get<Retrofit>().create(SaldoService::class.java) }
}