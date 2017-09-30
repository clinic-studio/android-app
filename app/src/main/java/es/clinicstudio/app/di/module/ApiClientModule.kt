package es.clinicstudio.app.di.module

import dagger.Module
import dagger.Provides
import es.clinicstudio.app.BuildConfig
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author vh @ recursividad.es
 */
@Module
class ApiClientModule {

    /**
     * Provides a [Retrofit] instance for performing API calls.
     */
    @Provides @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }
}