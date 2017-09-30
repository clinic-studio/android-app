package es.clinicstudio.app.domain.interactor

import es.clinicstudio.app.data.source.PostRepository
import es.clinicstudio.app.domain.entity.Post
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Use case for getting the list of posts in the blog.
 *
 * @author vh @ recursividad.es
 */
class GetPostsUseCase @Inject constructor(private val postRepository: PostRepository): UseCase<List<Post>, GetPostsUseCase.Params>() {

    class Params

    override fun buildObservable(params: Params?): Observable<List<Post>> {
        return Observable.create<List<Post>> {
            emitter ->
            val posts = getPosts()
            if (posts != null && !posts.isEmpty()) {
                emitter.onNext(posts)
            }

            emitter.onComplete()
        }
    }

    protected fun getPosts(): List<Post>? {
        return postRepository.getPosts()
    }
}