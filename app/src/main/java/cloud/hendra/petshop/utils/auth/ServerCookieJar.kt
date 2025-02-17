package cloud.hendra.petshop.utils.auth

import android.util.Log
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class ServerCookieJar(
    private val tokenManager: TokenManager
) : CookieJar {
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val refreshToken = tokenManager.getRefreshToken()
        return if (refreshToken != null) {
            listOf(
                Cookie.Builder()
                    .name("refresh")
                    .value(refreshToken)
                    .domain(url.host)
                    .path("/")
                    .build()
            )
        } else {
            emptyList()
        }
    }

    override fun saveFromResponse(
        url: HttpUrl,
        cookies: List<Cookie>
    ) {
        try {
            cookies.firstOrNull { it.name == "refresh" }?.let { cookie ->
                tokenManager.saveRefreshToken(cookie.value)
            }
        } catch (e: Exception) {
            Log.e("ServerCookieJar", "Failed to save cookies: ${e.message}")
        }
    }
}