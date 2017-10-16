package es.clinicstudio.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import es.clinicstudio.app.R
import es.clinicstudio.app.domain.entity.Post
import es.clinicstudio.app.ui.activity.BaseActivity
import es.clinicstudio.app.ui.lists.adapter.ListAdapter
import es.clinicstudio.app.ui.lists.adapter.PostListAdapter
import es.clinicstudio.app.ui.lists.holder.RowViewHolder
import es.clinicstudio.app.ui.presenter.PostListPresenter
import es.clinicstudio.app.ui.utils.Router
import es.clinicstudio.app.ui.view.PostListView
import kotlinx.android.synthetic.main.fragment_post_list.*
import javax.inject.Inject

/**
 * @author vh @ recursividad.es
 */
class PostListFragment: Fragment(), PostListView, RowViewHolder.OnItemClickedListener<Post> {

    @Inject lateinit var presenter: PostListPresenter
    @Inject lateinit var router: Router

    private var postListAdapter: PostListAdapter? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        // Inject dependencies
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        // Configure recycler view
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.list_separator))
        postRecyclerView.addItemDecoration(divider)

        // Configure swipe refresh layout
        swipeRefreshLayout.setOnRefreshListener({ presenter.loadPostPage() })
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent
        )

        // Load the list of posts from the blog
        presenter.loadPostPage()
    }

    /**
     * Display the loading layout.
     */
    override fun displayLoadingLayout() {
        //progressBar?.visibility = View.VISIBLE
    }

    /**
     * Hide the loading layout.
     */
    override fun hideLoadingLayout() {
        swipeRefreshLayout?.isRefreshing = false
        //progressBar?.visibility = View.GONE
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
        router.goToPostContentScreen(activity as BaseActivity, item.title.rendered, item.content.rendered)
    }
}