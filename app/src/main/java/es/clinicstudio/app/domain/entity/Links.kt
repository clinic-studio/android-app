package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName

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
)