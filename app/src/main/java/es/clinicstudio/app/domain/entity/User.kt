package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author vh @ recursividad.es
 */
data class User(
        val id: Int,
        val username: String,
        val name: String,
        val first_name: String,
        val last_name: String,
        val email: String,
        val url: String,
        val description: String,
        val link: String,
        val locale: Locale,
        val nickname: String,
        val slug: String,

        @SerializedName("_links")
        val links: Links
)