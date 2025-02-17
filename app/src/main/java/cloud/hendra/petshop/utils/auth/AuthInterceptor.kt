package cloud.hendra.petshop.utils.auth

import android.util.Log
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
        return if (shouldAddRefresh(requestPath)) {
            addRefreshToken(chain, request)
        } else if (shouldAddToken(requestPath)) {
            addTokenToRequest(chain, request)
        } else {
            chain.proceed(request)
        }
    }

    private fun shouldAddRefresh(request: String): Boolean {
        Log.d("AuthInterceptor", "shouldAddRefresh: ${request == "/api/v1/token/refresh"}")
        return request == "/api/v1/token/refresh"
    }

    private fun addRefreshToken(chain: Interceptor.Chain, originalRequest: Request): Response {
        return tokenManager.getRefreshToken()?.let { token ->
            val newRequest = originalRequest.newBuilder()
                .header("Cookie", "refresh=$token")
                .build()
            chain.proceed(newRequest)
        } ?: chain.proceed(originalRequest)
    }

    private fun shouldAddToken(request: String): Boolean {
        return excludedPaths.none { excluded ->
            request == excluded
        }
    }

    private fun addTokenToRequest(chain: Interceptor.Chain, originalRequest: Request): Response {
        return tokenManager.getAccessToken()?.let { token ->
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } ?: chain.proceed(originalRequest)
    }
}