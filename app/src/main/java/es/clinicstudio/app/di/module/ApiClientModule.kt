package es.clinicstudio.app.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import es.clinicstudio.app.BuildConfig
import es.clinicstudio.app.data.api.PostApiClient
import es.clinicstudio.app.data.api.serialization.CategoryApiClient
import es.clinicstudio.app.data.api.serialization.GsonDateTimeAdapter
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideRetrofit(converterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(converterFactory)
                .build()
    }

    /**
     * Provides a [retrofit2.Converter.Factory] for parsing the API requests
     * and the response from objects to JSON.
     */
    @Provides @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(DateTime::class.java, GsonDateTimeAdapter())

        return GsonConverterFactory.create(builder.create())
    }

    /**
     * Provides a [PostApiClient] for retrieving Posts from the API.
     */
    @Provides @Singleton
    fun providePostApiClient(retrofit: Retrofit): PostApiClient {
        return retrofit.create(PostApiClient::class.java)
    }

    /**
     * Provides a [CategoryApiClient] for retrieving Categories from the API.
     */
    @Provides @Singleton
    fun provideCategoryApiClient(retrofit: Retrofit): CategoryApiClient {
        return retrofit.create(CategoryApiClient::class.java)
    }
}