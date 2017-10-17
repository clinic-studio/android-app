package es.clinicstudio.app.di.module

import dagger.Module
import dagger.Provides
import es.clinicstudio.app.data.source.CategoryRepository
import es.clinicstudio.app.data.source.PostRepository
import es.clinicstudio.app.data.source.impl.CategoryRepositoryImpl
import es.clinicstudio.app.data.source.impl.PostRepositoryImpl
import javax.inject.Singleton

/**
 * @author vh @ recursividad.es
 */
@Module
class RepositoryModule {

    /**
     * Provides a [PostRepository] instance.
     *
     * @param[postRepository] Post repository implementation.
     * @return Post repository instance.
     */
    @Provides @Singleton
    fun providePostRepository(postRepository: PostRepositoryImpl): PostRepository {
        return postRepository
    }

    /**
     * Provides a [CategoryRepository] instance.
     *
     * @param[categoryRepository] Category repository implementation.
     * @return Category repository instance.
     */
    @Provides @Singleton
    fun provideCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository {
        return categoryRepository
    }
}