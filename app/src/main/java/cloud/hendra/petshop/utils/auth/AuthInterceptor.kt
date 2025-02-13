package cloud.hendra.petshop.utils.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager,
    private val excludedPaths: Set<String> = setOf(
        "api/v1/token/",
        "api/v1/token/refresh/"
    )
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (shouldAddToken(request)) {
            val token = tokenManager.getToken()
            if (token != null) {
                val newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return chain.proceed(newRequest)
            }
        }
        return chain.proceed(request)
    }

    private fun shouldAddToken(request: Request): Boolean {
        val path = request.url.encodedPath
        return excludedPaths.none { excluded ->
            path.startsWith(excluded) || request.url.host.contains(excluded)
        }
    }

}