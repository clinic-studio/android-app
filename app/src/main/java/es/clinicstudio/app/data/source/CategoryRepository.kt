package es.clinicstudio.app.data.source

import es.clinicstudio.app.domain.entity.Category
import es.clinicstudio.app.domain.entity.Page

/**
 * @author vh @ recursividad.es
 */
interface CategoryRepository {

    /**
     * Get the categories that match a [slug].
     *
     * @param[slug] Category slug.
     * @return List of categories that match the [slug]
     */
    fun getCategoriesBySlug(slug: String): Page<Category>?

    /**
     * Get the subcategories from a [parent] category.
     *
     * @param[parent] Parent category ID.
     * @param[page] Number of page to retrieve.
     * @param[size] Number of elements in the result.
     * @return List of subcategories in the [parent] category.
     */
    fun getCategoriesByParent(parent: Int, page: Int = 1, size: Int = 10): Page<Category>?
}