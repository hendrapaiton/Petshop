package cloud.hendra.petshop

import android.app.Application
import cloud.hendra.petshop.data.module.networkModule
import cloud.hendra.petshop.data.module.repositoryModule
import cloud.hendra.petshop.data.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}