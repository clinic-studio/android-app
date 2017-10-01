package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author vh @ recursividad.es
 */
data class Links(
        val self: Array<Link>,

        val collection: Array<Link>,

        val about: Array<Link>,

        val author: Array<Link>,

        val replies: Array<Link>,

        @SerializedName("version-history")
        val versionHistory: Array<Link>,

        @SerializedName("wp:attachment")
        val wpAttachment: Array<Link>,

        @SerializedName("wp:term")
        val wpTerm: Array<Link>,

        private
        val curies: Array<Link>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Links

        if (!Arrays.equals(self, other.self)) return false
        if (!Arrays.equals(collection, other.collection)) return false
        if (!Arrays.equals(about, other.about)) return false
        if (!Arrays.equals(author, other.author)) return false
        if (!Arrays.equals(replies, other.replies)) return false
        if (!Arrays.equals(versionHistory, other.versionHistory)) return false
        if (!Arrays.equals(wpAttachment, other.wpAttachment)) return false
        if (!Arrays.equals(wpTerm, other.wpTerm)) return false
        if (!Arrays.equals(curies, other.curies)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(self)
        result = 31 * result + Arrays.hashCode(collection)
        result = 31 * result + Arrays.hashCode(about)
        result = 31 * result + Arrays.hashCode(author)
        result = 31 * result + Arrays.hashCode(replies)
        result = 31 * result + Arrays.hashCode(versionHistory)
        result = 31 * result + Arrays.hashCode(wpAttachment)
        result = 31 * result + Arrays.hashCode(wpTerm)
        result = 31 * result + Arrays.hashCode(curies)
        return result
    }
}