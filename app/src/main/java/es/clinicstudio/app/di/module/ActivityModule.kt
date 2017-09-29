package es.clinicstudio.app.di.module

import dagger.Module
import es.clinicstudio.app.ui.activity.BaseActivity

/**
 * [Module] that provides the dependencies for the [android.app.Activity] members.
 *
 * @author vh @ recursividad.es
 */
@Module
class ActivityModule(private val activity: BaseActivity)

