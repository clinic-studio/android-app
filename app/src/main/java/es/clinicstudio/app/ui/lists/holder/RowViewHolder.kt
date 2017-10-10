package es.clinicstudio.app.ui.lists.holder

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Extension of [RecyclerView.ViewHolder] that allows to set the content of a
 * row item in a [RecyclerView], forcing to implement a recycled method that
 * resets the view to its original state.
 *
 * @author vh @ recursividad.es
 */
abstract class RowViewHolder<T>(itemView: View, onItemClickedListener: OnItemClickedListener<T>? = null): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            val content = getContent()
            if (content != null) {
                onItemClickedListener?.onItemClicked(content)
            }
        }
    }

    /**
     * Callback listener that will be invoked when the
     * row is clicked.
     */
    interface OnItemClickedListener<in T> {

        /**
         * Invoked when the row is clicked.
         *
         * @param[item] Content of the row.
         */
        fun onItemClicked(item: T)
    }

    /**
     * Recycle the view for further uses.
     */
    abstract fun recycle()

    /**
     * Get the content hold in this row.
     *
     * @return Content hold by the view.
     */
    abstract fun getContent(): T?

    /**
     * Display the content of the row item.
     *
     * @param content Content to be displayed.
     */
    abstract fun setContent(content: T)

    /**
     * Display the placeholder views while the content for
     * this view loads.
     */
    abstract fun placeholder()
}