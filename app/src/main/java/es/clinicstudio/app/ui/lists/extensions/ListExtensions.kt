package es.clinicstudio.app.ui.lists.extensions

import android.support.v7.util.SortedList

/**
 * List related extension functions.
 *
 * @author vh @ recursividad.es
 */


/**
 * Transforms the sorted list into a [List].
 */
fun <T> SortedList<T>.toList(): MutableList<T> {
    val result: MutableList<T> = ArrayList()
    (0..size()).mapTo(result) { get(it) }

    return result
}