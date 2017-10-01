package es.clinicstudio.app.domain.entity

/**
 * @author vh @ recursividad.es
 */
data class Link(
        val href: String,
        val name: String,
        val taxonomy: String,
        val embeddable: Boolean,
        val templated: Boolean
)