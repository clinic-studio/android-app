package es.clinicstudio.app.ui.lists.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import es.clinicstudio.app.ui.lists.holder.RowViewHolder
import java.util.*

/**
 * Extension of [RecyclerView.Adapter] to provide the basic and common
 * functionality for a list adapter.
 *
 * @author vh @ recursividad.es
 */
abstract class ListAdapter<in T, VH: RowViewHolder<T>>: RecyclerView.Adapter<VH>() {

    private var items: MutableList<T>? = null
    private var recyclerView: RecyclerView? = null

    /**
     * Bind a view holder with its content.
     *
     * @param holder View holder.
     * @param position Position of the item that should be displayed on the view holder.
     */
    override fun onBindViewHolder(holder: VH, position: Int) {
        if (position < itemCount) {
            holder.setContent(items!![position])
        }
    }

    /**
     * Get the total number of items in this list adapter.
     *
     * @return Number of items in the adapter.
     */
    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    /**
     * Recycle the view holder when its view has ben recycled.
     *
     * @param holder View holder to be recycled.
     */
    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)

        holder.recycle()
    }

    /**
     * Attaches the list adapter to the provided recycler view.
     *
     * @param recyclerView Recycler view that will be user to present the items of this list adapter.
     * @param orientation Layout orientation. Should be [LinearLayoutManager.HORIZONTAL] or [LinearLayoutManager.VERTICAL].
     * @param reverseLayout When set to true, layouts from end to start.
     */
    fun attach(recyclerView: RecyclerView, orientation: Int = LinearLayoutManager.VERTICAL, reverseLayout: Boolean = false) {
        // Dispose the current recycler view
        disposeRecyclerView()

        // Set the list adapter to the new recycler view
        recyclerView.adapter = this
        recyclerView.layoutManager = object: LinearLayoutManager(recyclerView.context, orientation, reverseLayout) {
            override fun supportsPredictiveItemAnimations(): Boolean {
                return true
            }
        }

        this.recyclerView = recyclerView
    }

    /**
     * Attaches the list adapter to the provided recycler view.
     *
     * @param recyclerView Recycler view that will be user to present the items of this list adapter.
     * @param layoutManager Layout manager to use to present the items in the recycler view.
     */
    fun attach(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager? = null) {
        // Dispose the current recycler view
        disposeRecyclerView()

        // Set the list adapter to the new recycler view
        recyclerView.adapter = this
        recyclerView.layoutManager = layoutManager ?: LinearLayoutManager(recyclerView.context)

        this.recyclerView = recyclerView
    }

    /**
     * Unset this recycler view attached to this list adapter.
     */
    fun disposeRecyclerView() {
        if (recyclerView != null) {
            recyclerView!!.adapter = null
            recyclerView!!.layoutManager = null

            recyclerView = null
        }
    }

    /**
     * Add a new item to the list adapter.
     *
     * @param item Item to be added.
     */
    fun add(item: T) {
        // Check if the item list is already instantiated
        if (items == null) {
            items = ArrayList()
        }

        // Add the item to the list
        val index = items!!.size
        items!!.add(item)

        // Notify the changes
        notifyItemInserted(index)
    }

    /**
     * Add a collection of items to the list adapter.
     *
     * @param collection Collection of items to be added.
     */
    fun add(collection: Collection<T>) {
        // Check if the item list is already instantiated
        if (items == null) {
            items = ArrayList()
        }

        // Add the items to the list
        val index = items!!.size
        items!!.addAll(collection)

        // Notify changes
        notifyItemRangeInserted(index, collection.size)
    }

    /**
     * Clear the content of this list adapter.
     */
    fun clear() {
        // Clear the content
        if (items != null) {
            val itemCount = itemCount
            items = ArrayList()

            // Notify the changes
            notifyItemRangeRemoved(0, itemCount)
        }
    }
}