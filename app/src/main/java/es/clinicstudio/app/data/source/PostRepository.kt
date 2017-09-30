package es.clinicstudio.app.data.source

import es.clinicstudio.app.domain.entity.Post

/**
 * @author vh @ recursividad.es
 */
interface PostRepository {

    /**
     * Get the list of most recent posts.
     *
     * @return Returns the list of most recent posts.
     */
    fun getPosts(): List<Post>?
}