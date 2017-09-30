package es.clinicstudio.app.data.source.impl

import android.net.ConnectivityManager
import es.clinicstudio.app.data.api.PostApiClient
import es.clinicstudio.app.data.source.PostRepository
import es.clinicstudio.app.data.source.Repository
import es.clinicstudio.app.domain.entity.Post
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author vh @ recursividad.es
 */
@Singleton
class PostRepositoryImpl @Inject constructor(
        connectivityManager: ConnectivityManager,
        private val postApiClient: PostApiClient)
    :
        PostRepository,
        Repository(connectivityManager, "repository.post") {

    /**
     * Get the list of most recent posts.
     *
     * @return Returns the list of most recent posts.
     */
    override fun getPosts(): List<Post>? {
        return fetchEntities(postApiClient.getPosts())
    }
}