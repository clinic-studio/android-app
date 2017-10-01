package es.clinicstudio.app.data.api

import es.clinicstudio.app.domain.entity.Post
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author vh @ recursividad.es
 */
interface PostApiClient {

    @GET("posts?_embed=true")
    fun getPosts(): Call<List<Post>>
}