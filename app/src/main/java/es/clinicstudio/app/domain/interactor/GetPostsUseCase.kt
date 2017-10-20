package es.clinicstudio.app.domain.interactor

import es.clinicstudio.app.data.source.PostRepository
import es.clinicstudio.app.domain.entity.Page
import es.clinicstudio.app.domain.entity.Post
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Use case for getting the list of posts in the blog.
 *
 * @author vh @ recursividad.es
 */
class GetPostsUseCase @Inject constructor(
        private val postRepository: PostRepository)
    :
        UseCase<Page<Post>, GetPostsUseCase.Params>() {

    data class Params(
            val page: Int = 1,
            val size: Int = 10
    )

    /**
     * Create a new observable to obtain the page of posts.
     *
     * @param[params] Parameters to apply when executing the use case.
     */
    override fun buildObservable(params: Params?): Observable<Page<Post>> {
        return Observable
                .create<Page<Post>> {
                    val posts = getPosts(params?.page ?: 1, params?.size ?: 10)
                    if (posts != null) {
                        it.onNext(posts)
                    }

                    it.onComplete()
                }
    }

    /**
     * Get the page of posts.
     *
     * @param[page] Number of page to obtain.
     * @param[size] Number of elements to obtain.
     */
    private fun getPosts(page: Int, size: Int): Page<Post>? {
        return postRepository.getPosts(page, size)
    }
}