package es.clinicstudio.app.ui.view

import es.clinicstudio.app.domain.entity.Post

/**
 * @author vh @ recursividad.es
 */
interface PostListView : View {

    /**
     * Display the list of posts.
     *
     * @param[posts] Lists of posts.
     */
    fun showPosts(posts: List<Post>)

    /**
     * Set the total amount of posts available in the blog.
     *
     * @param[i] Total page of posts.
     */
    fun setTotalPosts(i: Int)
}