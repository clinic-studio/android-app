package es.clinicstudio.app.data.source

import es.clinicstudio.app.domain.entity.Post

/**
 * @author vh @ recursividad.es
 */
interface PostRepository {

    /**
     * Get the list of most recent posts.
     *
     * @param[page] Number of page to retrieve.
     * @param[size] Number of elements in the result.
     * @return Returns the list of most recent posts.
     */
    fun getPosts(page: Int = 1, size: Int = 10): List<Post>?
}