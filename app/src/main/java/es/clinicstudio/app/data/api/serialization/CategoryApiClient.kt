package es.clinicstudio.app.data.api.serialization

import es.clinicstudio.app.domain.entity.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author vh @ recursividad.es
 */
interface CategoryApiClient {

    @GET("categories")
    fun getCategoriesBySlug(@Query("slug") slug: String): Call<List<Category>>

    @GET("categories")
    fun getCategoriesByParent(@Query("parent") parent: Int, @Query("page") page: Int = 1, @Query("per_page") size: Int = 10): Call<List<Category>>
}