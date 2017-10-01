package es.clinicstudio.app.di

import android.content.Context
import dagger.Component
import es.clinicstudio.app.di.module.ApiClientModule
import es.clinicstudio.app.di.module.ApplicationModule
import es.clinicstudio.app.di.module.RepositoryModule
import es.clinicstudio.app.ui.presenter.PostListPresenter
import es.clinicstudio.app.ui.utils.Navigator
import javax.inject.Singleton

/**
 * [Component] that provides those dependencies that are instantiated once and
 * whose lifetime is tied to the lifecycle of the [android.app.Application].
 *
 * @author vh @ recursividad.es
 */
@Singleton
@Component(
        modules = arrayOf(
                ApplicationModule::class,
                ApiClientModule::class,
                RepositoryModule::class
        )
)
interface ApplicationComponent {

    // DEPENDENCIES EXPOSED TO SUB-GRAPHS (sub-graph == dependent @Components)
    //      Application components
    fun context(): Context
    fun navigator(): Navigator

    fun postListPresenter(): PostListPresenter
}