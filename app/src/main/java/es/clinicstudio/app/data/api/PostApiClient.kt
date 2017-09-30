package es.clinicstudio.app.data.api

import es.clinicstudio.app.model.entity.Post
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author vh @ recursividad.es
 */
interface PostApiClient {

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}