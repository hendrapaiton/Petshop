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

    override fun getToken(): String? = prefs.getString("AUTH_TOKEN", null)

    override fun saveToken(login: LoginResponse) {
        prefs.edit().putString("AUTH_TOKEN", login.access).apply()
    }

    override fun clearToken() {
        prefs.edit().remove("AUTH_TOKEN").apply()
    }
}