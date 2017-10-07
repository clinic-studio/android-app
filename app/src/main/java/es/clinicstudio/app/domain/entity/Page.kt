package es.clinicstudio.app.domain.entity

/**
 * @author vh @ recursividad.es
 */
data class Page<T> (
        val content: List<T>?,
        val number: Int?,
        val capacity: Int?,
        val size: Int?,
        val isLast: Boolean?
)