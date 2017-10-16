package es.clinicstudio.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import es.clinicstudio.app.App
import es.clinicstudio.app.di.module.ApiClientModule
import es.clinicstudio.app.di.module.ApplicationModule
import es.clinicstudio.app.di.module.BuildersModule
import es.clinicstudio.app.di.module.RepositoryModule
import es.clinicstudio.app.ui.presenter.PostListPresenter
import es.clinicstudio.app.ui.utils.Router
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
                AndroidSupportInjectionModule::class,
                BuildersModule::class,
                ApplicationModule::class,
                ApiClientModule::class,
                RepositoryModule::class
        )
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): ApplicationComponent
    }

    // INJECTORS
    //      Application injector
    fun inject(app: App)

    // DEPENDENCIES EXPOSED TO SUB-GRAPHS (sub-graph == dependent @Components)
    //      Application components
    fun context(): Context
    fun router(): Router

    //      Presenters
    fun postListPresenter(): PostListPresenter
}