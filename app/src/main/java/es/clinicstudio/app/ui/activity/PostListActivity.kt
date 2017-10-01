package es.clinicstudio.app.ui.activity

import android.os.Bundle
import es.clinicstudio.app.R
import es.clinicstudio.app.domain.entity.Post
import es.clinicstudio.app.ui.lists.adapter.PostListAdapter
import es.clinicstudio.app.ui.presenter.PostListPresenter
import es.clinicstudio.app.ui.view.PostListView
import kotlinx.android.synthetic.main.activity_post_list.*
import javax.inject.Inject

/**
 * Post list screen [android.app.Activity].
 *
 * @author vh @ recursividad.es
 */
class PostListActivity : BaseActivity(), PostListView {

    @Inject
    lateinit var presenter: PostListPresenter


    private var postListAdapter: PostListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        // Inject dependencies
        activityComponent.inject(this)

        // Initialize the presenter
        presenter.setView(this)

        // Initialize the post list adapter
        postListAdapter = PostListAdapter(layoutInflater)
        postListAdapter?.attach(recyclerView = postRecyclerView)
    }

    override fun onStart() {
        super.onStart()

        // Load the list of posts from the blog
        presenter.loadPosts()
    }

    /**
     * Display the list of posts.
     *
     * @param posts Lists of posts.
     */
    override fun showPosts(posts: List<Post>) {
        postListAdapter?.clear()
        postListAdapter?.add(posts)
    }
}