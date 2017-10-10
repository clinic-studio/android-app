package es.clinicstudio.app.ui.lists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import es.clinicstudio.app.R
import es.clinicstudio.app.domain.entity.Post
import es.clinicstudio.app.ui.lists.holder.PostRowViewHolder
import es.clinicstudio.app.ui.lists.holder.RowViewHolder

/**
 * @author vh @ recursividad.es
 */
class PostListAdapter(
        private val layoutInflater: LayoutInflater,
        private val onItemClickedListener: RowViewHolder.OnItemClickedListener<Post>? = null
): ListAdapter<Post, PostRowViewHolder>(Post::class.java) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostRowViewHolder {
        return PostRowViewHolder(layoutInflater.inflate(R.layout.list_item_post, parent, false), onItemClickedListener)
    }
}