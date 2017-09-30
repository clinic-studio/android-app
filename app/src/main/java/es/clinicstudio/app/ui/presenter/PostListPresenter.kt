package es.clinicstudio.app.ui.presenter

import es.clinicstudio.app.domain.entity.Post
import es.clinicstudio.app.domain.interactor.DefaultObserver
import es.clinicstudio.app.domain.interactor.GetPostsUseCase
import es.clinicstudio.app.ui.view.PostListView
import javax.inject.Inject

/**
 * [Presenter] for the post list view.
 *
 * @author vh @ recursividad.es
 */
class PostListPresenter
    @Inject constructor(private val getPostsUseCase: GetPostsUseCase)
    : Presenter<PostListView>() {

    private var view: PostListView? = null

    /**
     * Set the view that will display the data.
     *
     * @param view View.
     */
    override fun setView(view: PostListView) {
        this.view = view
    }

    /**
     * Load the list of posts from the API to the view.
     */
    fun loadPosts() {
        getPostsUseCase.execute(object: DefaultObserver<List<Post>>() {
            override fun onNext(posts: List<Post>) {
                view?.showPosts(posts)
            }
        })
    }
}