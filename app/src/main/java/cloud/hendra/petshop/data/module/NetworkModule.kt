package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.utils.Constant.Companion.BASE_URL
import cloud.hendra.petshop.data.remote.IndexService
import cloud.hendra.petshop.utils.auth.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(
                AuthInterceptor(
                    tokenManager = get(),
                    excludedPaths = setOf(
                        "api/v1/token/",
                        "api/v1/token/refresh/",
                    )
                )
            )
            .addInterceptor(HttpLoggingInterceptor().apply {
                HttpLoggingInterceptor.Level.BODY
            })
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
}