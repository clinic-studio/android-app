package es.clinicstudio.app.ui.presenter

import es.clinicstudio.app.domain.entity.Page
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

    private val defaultPageSize = 10
    private val loading: MutableList<Int> = ArrayList()

    /**
     * Set the view that will display the data.
     *
     * @param view View.
     */
    override fun setView(view: PostListView) {
        this.view = view
    }

    /**
     * Load a page of posts from the API to the view.
     *
     * @param[page] Number of the requested page of posts.
     */
    fun loadPostPage(page: Int = 1) {
        loading.add(page)

        view?.displayLoadingLayout()
        getPostsUseCase.execute(
                // Observer
                object: DefaultObserver<Page<Post>>() {
                    override fun onNext(next: Page<Post>) {
                        loading.remove(page)

                        view?.showPosts(next.content)
                        view?.setTotalPosts(next.collectionSize ?: Int.MAX_VALUE)

                        if (loading.isEmpty()) {
                            view?.hideLoadingLayout()
                        }
                    }

                    override fun onError(e: Throwable) {
                        loading.remove(page)
                    }
                },
                // Params
                GetPostsUseCase.Params(page, defaultPageSize))
    }

    /**
     * Load the post in indicated [position].
     *
     * @param[position] Position of the requested post.
     */
    fun loadPost(position: Int) {
        val page = (position / defaultPageSize) + 1

        if (!loading.contains(page)) {
            loadPostPage(page)
        }
    }
}