package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * @author vh @ recursividad.es
 */
data class Term(
        val id: Int,
        val link: String,
        val name: String,
        val slug: String,
        val taxonomy: String,

        @SerializedName("_links")
        val links: Links
)