package cloud.hendra.petshop.utils.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import cloud.hendra.petshop.data.remote.dto.LoginResponse

class SecureTokenManager(private val context: Context) : TokenManager {
    private val prefs: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            "secure_token",
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun getAccessToken(): String? = prefs.getString("ACCESS_TOKEN", null)

    override fun saveAccessToken(login: LoginResponse) {
        prefs.edit().putString("ACCESS_TOKEN", login.access).apply()
    }

    override fun clearAccessToken() {
        prefs.edit().remove("ACCESS_TOKEN").apply()
    }

    override fun getRefreshToken(): String? = prefs.getString("REFRESH_TOKEN", null)

    override fun saveRefreshToken(refresh: String) {
        prefs.edit().putString("REFRESH_TOKEN", refresh).apply()
    }

}