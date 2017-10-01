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
        val links: Links
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (date != other.date) return false
        if (date_gmt != other.date_gmt) return false
        if (modified != other.modified) return false
        if (modified_gmt != other.modified_gmt) return false
        if (guid != other.guid) return false
        if (slug != other.slug) return false
        if (status != other.status) return false
        if (type != other.type) return false
        if (link != other.link) return false
        if (title != other.title) return false
        if (content != other.content) return false
        if (excerpt != other.excerpt) return false
        if (author != other.author) return false
        if (featured_media != other.featured_media) return false
        if (comment_status != other.comment_status) return false
        if (ping_status != other.ping_status) return false
        if (sticky != other.sticky) return false
        if (template != other.template) return false
        if (!Arrays.equals(meta, other.meta)) return false
        if (!Arrays.equals(categories, other.categories)) return false
        if (!Arrays.equals(tags, other.tags)) return false
        if (links != other.links) return false

        return true
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
        return result
    }
}