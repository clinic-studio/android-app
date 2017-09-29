package es.clinicstudio.app.di

import dagger.Component
import es.clinicstudio.app.di.module.ActivityModule
import es.clinicstudio.app.ui.activity.BaseActivity

/**
 * [Component] that provides those dependencies that are instantiate in
 * each [android.app.Activity] and whose lifetime is tide to it.
 *
 * @author vh @ recursividad.es
 */
@ActivityScope
@Component(
        dependencies = arrayOf( ApplicationComponent::class ),
        modules = arrayOf( ActivityModule::class )
)
interface ActivityComponent {

    // INJECTORS
    //      Activity injectors
    fun inject(activity: BaseActivity)
}

