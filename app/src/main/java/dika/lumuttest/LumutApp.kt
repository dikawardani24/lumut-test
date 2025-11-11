package dika.lumuttest

import android.app.Application
import dika.lumuttest.di.ApiModule
import dika.lumuttest.di.UseCaseModule
import dika.lumuttest.di.VIewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LumutApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LumutApp)
            modules(
                ApiModule.get(),
                UseCaseModule.get(),
                VIewModelModule.get()
            )
        }
    }
}