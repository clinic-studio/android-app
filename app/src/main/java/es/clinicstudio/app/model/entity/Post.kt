package es.clinicstudio.app.model.entity

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
        val tags: Array<Int>
)