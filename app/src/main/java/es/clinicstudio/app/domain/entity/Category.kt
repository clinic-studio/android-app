package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * @author vh @ recursividad.es
 */
data class Category(
        val id: Int,
        val count: Int,
        val description: String,
        val link: String,
        val name: String,
        val slug: String,
        val taxonomy: String,
        val parent: Int,
        val meta: Array<Int>,

        @SerializedName("_links")
        val links: Links
)