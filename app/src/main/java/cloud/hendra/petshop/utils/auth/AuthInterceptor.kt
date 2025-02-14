package cloud.hendra.petshop.utils.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager,
    private val excludedPaths: Set<String> = setOf(
        "/api/v1/",
        "/api/v1/token/",
        "/api/v1/token/refresh/"
    )
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val pathSegments = request.url.pathSegments
        val requestPath = "/${pathSegments.joinToString("/")}"
        return if (shouldAddToken(requestPath)) {
            addTokenToRequest(chain, request)
        } else {
            chain.proceed(request)
        }
    }

    private fun shouldAddToken(request: String): Boolean {
        return excludedPaths.none { excluded ->
            request.startsWith(excluded)
        }
    }

    private fun addTokenToRequest(chain: Interceptor.Chain, originalRequest: Request): Response {
        return tokenManager.getToken()?.let { token ->
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } ?: chain.proceed(originalRequest)
    }
}