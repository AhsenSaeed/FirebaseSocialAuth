package spartons.com.firebasesocialauthentication.backend

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import spartons.com.firebasesocialauthentication.koinDI.firebaseModule
import spartons.com.firebasesocialauthentication.activities.social.di.socialActivityModule


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/20/19}
 */

class MyCustomApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyCustomApp)
            modules(
                listOf(
                    firebaseModule, socialActivityModule
                )
            )
        }
    }
}