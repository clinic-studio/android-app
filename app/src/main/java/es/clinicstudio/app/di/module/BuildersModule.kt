package es.clinicstudio.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import es.clinicstudio.app.ui.activity.*

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
    abstract fun contributePostListActivity(): PostListActivity

    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class) )
    abstract fun contributePostContentActivity(): PostContentActivity
}