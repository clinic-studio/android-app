package es.clinicstudio.app.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import es.clinicstudio.app.App
import es.clinicstudio.app.di.ActivityComponent
import es.clinicstudio.app.di.DaggerActivityComponent
import es.clinicstudio.app.di.module.ActivityModule
import es.clinicstudio.app.ui.utils.Router
import io.fabric.sdk.android.Fabric
import javax.inject.Inject


/**
 * Base [android.app.Activity] class for every Activity within the [App].
 *
 * @author vh @ recursividad.es
 */
open class BaseActivity: AppCompatActivity() {

    @Inject lateinit var context: Context
    @Inject lateinit var router: Router

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a new activity component for this activity scope dependencies injection
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent((application as App).applicationComponent)
                .activityModule(ActivityModule(this))
                .build()

        // Inject dependencies for this activity
        activityComponent.inject(this)

        // Initialize Crashlytics
        Fabric.with(this, Crashlytics())
    }
}