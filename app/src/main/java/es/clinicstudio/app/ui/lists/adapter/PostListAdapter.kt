package es.clinicstudio.app.ui.lists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import es.clinicstudio.app.R
import es.clinicstudio.app.domain.entity.Post
import es.clinicstudio.app.ui.lists.holder.PostRowViewHolder

/**
 * @author vh @ recursividad.es
 */
class PostListAdapter(private val layoutInflater: LayoutInflater): ListAdapter<Post, PostRowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostRowViewHolder {
        return PostRowViewHolder(layoutInflater.inflate(R.layout.list_item_post, parent, false))
    }
}