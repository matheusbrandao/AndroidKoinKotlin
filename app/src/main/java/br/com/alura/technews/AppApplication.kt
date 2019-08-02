package br.com.alura.technews

import android.app.Application
import br.com.alura.technews.di.modules.appModules
import br.com.alura.technews.di.modules.networksModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Indicando o contexto ao Koin
            androidContext(this@AppApplication)
            // Inicializando os modulos
            modules(listOf(appModules, networksModule))
        }
    }

}