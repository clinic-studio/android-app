package es.clinicstudio.app.data.source.impl

import android.net.ConnectivityManager
import es.clinicstudio.app.data.api.serialization.CategoryApiClient
import es.clinicstudio.app.data.source.CategoryRepository
import es.clinicstudio.app.data.source.Repository
import es.clinicstudio.app.domain.entity.Category
import es.clinicstudio.app.domain.entity.Page
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author vh @ recursividad.es
 */
@Singleton
class CategoryRepositoryImpl @Inject constructor(
        connectivityManager: ConnectivityManager,
        private val categoryApiClient: CategoryApiClient)
    :
        CategoryRepository,
        Repository(connectivityManager, "repository.category") {

    /**
     * Get the categories that match a [slug].
     *
     * @param[slug] Category slug.
     * @return List of categories that match the [slug]
     */
    override fun getCategoriesBySlug(slug: String): Page<Category>? {
        return fetchPage(categoryApiClient.getCategoriesBySlug(slug))
    }

    /**
     * Get the subcategories from a [parent] category.
     *
     * @param[parent] Parent category ID.
     * @param[page] Number of page to retrieve.
     * @param[size] Number of elements in the result.
     * @return List of subcategories in the [parent] category.
     */
    override fun getCategoriesByParent(parent: Int, page: Int, size: Int): Page<Category>? {
        return fetchPage(categoryApiClient.getCategoriesByParent(parent, page, size))
    }

}