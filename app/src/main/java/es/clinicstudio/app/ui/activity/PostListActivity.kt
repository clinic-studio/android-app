package es.clinicstudio.app.ui.activity

import android.os.Bundle
import es.clinicstudio.app.R
import es.clinicstudio.app.domain.entity.Post
import es.clinicstudio.app.ui.presenter.PostListPresenter
import es.clinicstudio.app.ui.view.PostListView
import javax.inject.Inject

/**
 * Post list screen [android.app.Activity].
 *
 * @author vh @ recursividad.es
 */
class PostListActivity : BaseActivity(), PostListView {

    @Inject
    var presenter: PostListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        // Inject dependencies
        activityComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()

        // Load the list of posts from the blog
        presenter?.loadPosts()
    }

    /**
     * Display the list of posts.
     *
     * @param posts Lists of posts.
     */
    override fun showPosts(posts: List<Post>) {

    }
}