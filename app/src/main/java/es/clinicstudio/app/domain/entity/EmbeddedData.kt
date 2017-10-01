package es.clinicstudio.app.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * @author vh @ recursividad.es
 */
data class EmbeddedData(
        val author: Array<User>,

        @SerializedName("wp:term")
        val terms: Array<Array<Term>>
)