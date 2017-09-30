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
open abstract class RowViewHolder<in T>(itemView: View): RecyclerView.ViewHolder(itemView) {

    /**
     * Recycle the view for further uses.
     */
    abstract fun recycle()

    /**
     * Set the content of the row item.
     *
     * @param content Content to be displayed.
     */
    abstract fun setContent(content: T)
}