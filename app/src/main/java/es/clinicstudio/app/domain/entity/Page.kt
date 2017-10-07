package es.clinicstudio.app.domain.entity

/**
 * @author vh @ recursividad.es
 */
data class Page<out T> (
        val content: List<T>,
        val page: Int,
        val size: Int,
        val capacity: Int,
        val collectionPages: Int?,
        val collectionSize: Int?
) {

    /**
     * Get the element in the specified [i] position.
     *
     * @param[i] Position of the desired element.
     */
    operator fun get(i: Int): T? {
        return if (content.size > i) {
            content[i]
        }
        else {
            null
        }
    }
}