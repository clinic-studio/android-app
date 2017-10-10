package es.clinicstudio.app.ui.lists.adapter

import android.support.v7.util.SortedList
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import es.clinicstudio.app.ui.lists.holder.RowViewHolder

/**
 * Extension of [RecyclerView.Adapter] to provide the basic and common
 * functionality for a list adapter.
 *
 * @author vh @ recursividad.es
 */
abstract class ListAdapter<T: Comparable<T>, VH: RowViewHolder<T>>(klass: Class<T>): RecyclerView.Adapter<VH>() {

    var size: Int? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private var recyclerView: RecyclerView? = null

    private var items: SortedList<T> = SortedList(
            klass,
            object: SortedList.Callback<T>() {
                override fun compare(o1: T, o2: T): Int {
                    return o1.compareTo(o2)
                }

                override fun onChanged(position: Int, count: Int) {
                    notifyItemRangeChanged(position, count)
                }

                override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                    return (oldItem.hashCode() == newItem.hashCode())
                }

                override fun areItemsTheSame(item1: T, item2: T): Boolean {
                    return item1 == item2
                }

                override fun onInserted(position: Int, count: Int) {
                    notifyItemRangeChanged(position, count)
                }

                override fun onRemoved(position: Int, count: Int) {
                    notifyItemRangeChanged(position, count)
                }

                override fun onMoved(fromPosition: Int, toPosition: Int) {
                    notifyItemMoved(fromPosition, toPosition)
                }
            })

    var notLoadedItemCallback: NotLoadedItemCallback? = null

    /**
     * Callback invoked when an item that is not yet present in
     * the list has been requested to be shown.
     */
    interface NotLoadedItemCallback {

        /**
         * A new item that has not yet been loaded has been
         * requested to be shown.
         *
         * @param[position] Position of the new requested item.
         */
        fun onNotLoadedItemRequested(position: Int)
    }

    /**
     * Bind a view holder with its content.
     *
     * @param holder View holder.
     * @param position Position of the item that should be displayed on the view holder.
     */
    override fun onBindViewHolder(holder: VH, position: Int) {
        if ((items.size()) > position) {
            holder.setContent(items[position])
        }
        else {
            notLoadedItemCallback?.onNotLoadedItemRequested(position)
            holder.placeholder()
        }
    }

    /**
     * Get the total page of items in this list adapter.
     *
     * @return Number of items in the adapter.
     */
    override fun getItemCount(): Int {
        return size ?: Int.MAX_VALUE
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
        detach()

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
        // Detach the current recycler view
        detach()

        // Set the list adapter to the new recycler view
        recyclerView.adapter = this
        recyclerView.layoutManager = layoutManager ?: LinearLayoutManager(recyclerView.context)

        this.recyclerView = recyclerView
    }

    /**
     * Unset this recycler view attached to this list adapter.
     */
    fun detach() {
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
        items.add(item)
    }

    /**
     * Add a collection of items to the list adapter.
     *
     * @param collection Collection of items to be added.
     */
    fun add(collection: Collection<T>) {
        items.addAll(collection)
    }

    /**
     * Clear the content of this list adapter.
     */
    fun clear() {
        items.clear()
    }
}