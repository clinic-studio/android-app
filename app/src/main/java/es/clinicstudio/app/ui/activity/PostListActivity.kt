package es.clinicstudio.app.ui.activity

import android.os.Bundle
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
    var onItemClickedListener: RowViewHolder.OnItemClickedListener<Post>? = null


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

        // Load the list of posts from the blog
        presenter.loadPostPage()
    }

    /**
     * Display the list of posts.
     *
     * @param posts Lists of posts.
     */
    override fun showPosts(posts: List<Post>) {
        postListAdapter?.add(posts)
    }

    override fun setTotalPosts(i: Int) {
        postListAdapter?.size = i
    }

    override fun onItemClicked(item: Post) {
        router.goToPostContentScreen(this, item.title.rendered, item.content.rendered)
    }
}