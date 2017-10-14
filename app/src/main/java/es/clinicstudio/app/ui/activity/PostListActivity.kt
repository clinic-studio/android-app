package es.clinicstudio.app.ui.activity

import android.os.Bundle
import android.view.View
import es.clinicstudio.app.R
import es.clinicstudio.app.domain.entity.Post
import es.clinicstudio.app.ui.lists.adapter.ListAdapter
import es.clinicstudio.app.ui.lists.adapter.PostListAdapter
import es.clinicstudio.app.ui.lists.holder.RowViewHolder
import es.clinicstudio.app.ui.presenter.PostListPresenter
import es.clinicstudio.app.ui.view.PostListView
import kotlinx.android.synthetic.main.activity_post_list.*
import javax.inject.Inject

/**
 * Post list screen [android.app.Activity].
 *
 * @author vh @ recursividad.es
 */
class PostListActivity: BaseActivity(), PostListView, RowViewHolder.OnItemClickedListener<Post> {

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
        postListAdapter = PostListAdapter(layoutInflater, this)
        postListAdapter?.attach(recyclerView = postRecyclerView)
        postListAdapter?.notLoadedItemCallback = object : ListAdapter.NotLoadedItemCallback {
            override fun onNotLoadedItemRequested(position: Int) {
                presenter.loadPost(position)
            }
        }

        // Configure action bar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Configure swipe refresh layout
        swipeRefreshLayout.setOnRefreshListener({ presenter.loadPostPage() })
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent
        );

        // Load the list of posts from the blog
        presenter.loadPostPage()
    }

    /**
     * Display the loading layout.
     */
    override fun displayLoadingLayout() {
        progressBar?.visibility = View.VISIBLE
    }

    /**
     * Hide the loading layout.
     */
    override fun hideLoadingLayout() {
        swipeRefreshLayout?.isRefreshing = false
        progressBar?.visibility = View.GONE
    }

    /**
     * Display the list of posts.
     *
     * @param posts Lists of posts.
     */
    override fun showPosts(posts: List<Post>) {
        postListAdapter?.add(posts)
    }

    /**
     * Set the total amount of posts available in the blog.
     *
     * @param[i] Total page of posts.
     */
    override fun setTotalPosts(i: Int) {
        postListAdapter?.size = i
    }

    /**
     * Invoked when a row is clicked.
     *
     * @param[item] Content of the row.
     */
    override fun onItemClicked(item: Post) {
        router.goToPostContentScreen(this, item.title.rendered, item.content.rendered)
    }
}