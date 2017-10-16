package es.clinicstudio.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import es.clinicstudio.app.ui.activity.BaseActivity
import es.clinicstudio.app.ui.activity.HomeActivity
import es.clinicstudio.app.ui.activity.PostContentActivity
import es.clinicstudio.app.ui.activity.SplashActivity
import es.clinicstudio.app.ui.fragment.PostListFragment

/**
 * @author vh @ recursividad.es
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class) )
    abstract fun contributeBaseActivity(): BaseActivity

    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class) )
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class) )
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class) )
    abstract fun contributePostContentActivity(): PostContentActivity

    @ContributesAndroidInjector
    abstract fun contributePostListFragment(): PostListFragment
}