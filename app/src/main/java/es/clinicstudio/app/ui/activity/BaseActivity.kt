package es.clinicstudio.app.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.AndroidInjection
import es.clinicstudio.app.App
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inject dependencies for this activity
        AndroidInjection.inject(this)

        // Initialize Crashlytics
        Fabric.with(this, Crashlytics())

        // Initialize Firebase Analytics
        FirebaseAnalytics.getInstance(this)
    }
}