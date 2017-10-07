package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

/**
 * @author vh @ recursividad.es
 */
data class Post(
        val id: Int,
        val date: DateTime,
        val date_gmt: DateTime,
        val modified: DateTime,
        val modified_gmt: DateTime,
        val guid: Data,
        val slug: String,
        val status: String,
        val type: String,
        val link: String,
        val title: Data,
        val content: Data,
        val excerpt: Data,
        val author: Int,
        val featured_media: Int,
        val comment_status: String,
        val ping_status: String,
        val sticky: Boolean,
        val template: String,
        val meta: Array<Int>,
        val categories: Array<Int>,
        val tags: Array<Int>,

        @SerializedName("_links")
        val links: Links,

        @SerializedName("_embedded")
        val embedded: EmbeddedData,

        var media: List<Media>?
)