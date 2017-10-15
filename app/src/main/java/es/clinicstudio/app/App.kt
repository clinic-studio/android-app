package es.clinicstudio.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import es.clinicstudio.app.di.ApplicationComponent
import es.clinicstudio.app.di.DaggerApplicationComponent
import es.clinicstudio.app.di.module.ApplicationModule
import io.fabric.sdk.android.Fabric

/**
 * Clinic Studio [Application] for Android.
 *
 * @author vh @ recursividad.es
 */
class App: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializeInjector()

        // Initialize Crashlytics
        Fabric.with(this, Crashlytics())
    }

    private fun initializeInjector() {
       applicationComponent = DaggerApplicationComponent.builder()
               .applicationModule(ApplicationModule(this))
               .build()
    }
}