package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * @author vh @ recursividad.es
 */
data class Media(
        @SerializedName("media_type")
        val mediaType: String,

        @SerializedName("mime_type")
        val mimeType: String,

        @SerializedName("source_url")
        val sourceUrl: String
)