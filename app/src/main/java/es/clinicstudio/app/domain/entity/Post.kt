package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import java.util.*

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

        var media: List<Media>?,
        var position: Int
): Comparable<Post> {

    override fun compareTo(other: Post): Int {
        // Most recent first
        return other.date.compareTo(date)
    }

    override fun equals(other: Any?): Boolean {
        return if (other != null && other is Post) {
            id == other.id
        }
        else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + date.hashCode()
        result = 31 * result + date_gmt.hashCode()
        result = 31 * result + modified.hashCode()
        result = 31 * result + modified_gmt.hashCode()
        result = 31 * result + guid.hashCode()
        result = 31 * result + slug.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + excerpt.hashCode()
        result = 31 * result + author
        result = 31 * result + featured_media
        result = 31 * result + comment_status.hashCode()
        result = 31 * result + ping_status.hashCode()
        result = 31 * result + sticky.hashCode()
        result = 31 * result + template.hashCode()
        result = 31 * result + Arrays.hashCode(meta)
        result = 31 * result + Arrays.hashCode(categories)
        result = 31 * result + Arrays.hashCode(tags)
        result = 31 * result + links.hashCode()
        result = 31 * result + embedded.hashCode()
        result = 31 * result + (media?.hashCode() ?: 0)
        result = 31 * result + position
        return result
    }
}