package es.clinicstudio.app.data.api

import es.clinicstudio.app.domain.entity.Media
import es.clinicstudio.app.domain.entity.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author vh @ recursividad.es
 */
interface PostApiClient {

    @GET("posts?_embed=true")
    fun getPosts(@Query("page") page: Int = 1, @Query("per_page") size: Int = 10): Call<List<Post>>

    @GET("media")
    fun getMediaForPost(@Query("parent") postId: Int): Call<List<Media>>
}