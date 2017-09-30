package es.clinicstudio.app.di.module

import dagger.Module
import dagger.Provides
import es.clinicstudio.app.data.source.PostRepository
import es.clinicstudio.app.data.source.impl.PostRepositoryImpl
import javax.inject.Singleton

/**
 * @author vh @ recursividad.es
 */
@Module
class RepositoryModule {

    @Provides @Singleton
    fun providePostRepository(postRepository: PostRepositoryImpl): PostRepository {
        return postRepository
    }
}