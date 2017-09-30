package es.clinicstudio.app.di.module

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import es.clinicstudio.app.App
import es.clinicstudio.app.ui.utils.Navigator
import javax.inject.Singleton


/**
 * [Module] that provides the dependencies for the [android.app.Application] members.
 *
 * @author vh @ recursividad.es
 */
@Module
class ApplicationModule(private val app: App) {

    /**
     * Provides the application [Context].
     *
     * @return Application context.
     */
    @Provides @Singleton
    fun provideApplicationContext(): Context {
        return app.applicationContext
    }

    /**
     * Provide the application [ConnectivityManager].
     *
     * @return Connectivity manager.
     */
    @Provides @Singleton
    fun provideConnectivityManager(): ConnectivityManager {
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /**
     * Provides the application [Navigator].
     *
     * @param context Auto-injected application context.
     * @return Application navigator.
     */
    @Provides @Singleton
    fun provideNavigator(context: Context): Navigator {
        return Navigator(context)
    }
}