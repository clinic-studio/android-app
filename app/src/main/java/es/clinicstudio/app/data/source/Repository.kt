package es.clinicstudio.app.data.source

import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.GsonBuilder
import es.clinicstudio.app.BuildConfig
import es.clinicstudio.app.domain.entity.Page
import es.clinicstudio.app.domain.error.CommunicationException
import es.clinicstudio.app.domain.error.NetworkUnreachableException
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

/**
 * Base repository class with some useful and common methods already implemented.
 *
 * @author vh @ recursividad.es
 */
abstract class Repository(
        private val connectivityManager: ConnectivityManager,
        private val loggingTag: String
) {

    /**
     * Perform the specified [call] and get its response.
     *
     * @param[T] Type of the elements expected in the call response body.
     * @param[call] API call to perform.
     *
     * @return Response obtained after executing the specified [call].
     *
     * @throws IllegalArgumentException Thrown if some invalid argument is passed to the method.
     * @throws NetworkUnreachableException Thrown if the device cannot establish a connection to the internet.
     * @throws CommunicationException Thrown if a communication problem occurred when executing the call.
     */
    @Throws(IllegalArgumentException::class, NetworkUnreachableException::class, CommunicationException::class)
    private fun <T> call(call: Call<T>?): Response<T> {
        // Check input
        if (call == null) {
            Log.e(loggingTag, "ARGUMENTS :: Couldn't fetch an entity from the API without a call!")
            throw IllegalArgumentException()
        }

        // Check that the device can connect to the internet
        if (!canConnect()) {
            Log.e(loggingTag, "CONNECTIVITY :: The device doesn't seem to have an active Internet connection")
            throw NetworkUnreachableException()
        }

        val response: Response<T>

        try {
            // Execute the API call
            response = call.execute()

            // Verify that it was possible to connect to the API and obtain a response
            if (response == null) {
                Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'. Null response", method(call), url(call)))
                throw CommunicationException()
            }

            // Verify that the response was successful
            if (response.isSuccessful) {
                Log.v(loggingTag, String.format("REST API :: Success when calling '%s %s'. The response was:\nStatus: %s\n\n%s\n", method(call), url(call), status(response), body(response)))
            } else {
                Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'. The response was:\nStatus: %s\n\n%s\n", method(call), url(call), status(response), body(response)))
            }

        } catch (e: IOException) {
            Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'.", method(call), url(call)), e)
            throw CommunicationException(cause = e)
        }

        return response
    }

    /**
     * Executed the specified [call] to retrieve one entity from the API.
     *
     * @param[T] Type of the entity expected in the [call] response body.
     * @param[call] API call to perform.
     *
     * @return Entity obtained after executing the specified [call].
     *
     * @throws IllegalArgumentException Thrown if some invalid argument is passed to the method.
     * @throws NetworkUnreachableException Thrown if the device cannot establish a connection to the internet.
     * @throws CommunicationException Thrown if a communication problem occurred when executing the call.
     */
    @Throws(IllegalArgumentException::class, NetworkUnreachableException::class, CommunicationException::class)
    protected fun <T> fetchEntity(call: Call<T>?): T? {
        val response = call(call)

        var result: T? = null
        if (response.isSuccessful) {
            result = response.body()
        }

        return result
    }

    /**
     * Executed the specified [call] to retrieve a list of entities from the API.
     *
     * @param[T] Type of the entities expected in the [call] response body.
     * @param[call] API call to perform.
     *
     * @return List of entities obtained after executing the specified [call].
     *
     * @throws IllegalArgumentException Thrown if some invalid argument is passed to the method.
     * @throws NetworkUnreachableException Thrown if the device cannot establish a connection to the internet.
     * @throws CommunicationException Thrown if a communication problem occurred when executing the call.
     */
    @Throws(IllegalArgumentException::class, NetworkUnreachableException::class, CommunicationException::class)
    protected fun <T> fetchEntities(call: Call<List<T>>?): List<T>? {
        val response = call(call)

        var result: List<T>? = null
        if (response.isSuccessful) {
            result = response.body()
        }

        return result
    }

    /**
     * Executed the specified [call] to retrieve a page of entities from the API.
     *
     * @param[T] Type of the entities expected in the [call] response body.
     * @param[call] API call to perform.
     *
     * @return Page of entities obtained after executing the specified [call].
     *
     * @throws IllegalArgumentException Thrown if some invalid argument is passed to the method.
     * @throws NetworkUnreachableException Thrown if the device cannot establish a connection to the internet.
     * @throws CommunicationException Thrown if a communication problem occurred when executing the call.
     */
    @Throws(IllegalArgumentException::class, NetworkUnreachableException::class, CommunicationException::class)
    protected fun<T> fetchPage(call: Call<List<T>>): Page<T>? {
        var result: Page<T>? = null

        val response = call(call)
        if (response.isSuccessful) {
            val content = response.body()
            val page = call.request().url().queryParameter("page")!!.toInt()
            val capacity = call.request().url().queryParameter("per_page")!!.toInt()
            val totalPages = response.headers()?.get(BuildConfig.TOTAL_PAGES_HEADER)?.toInt()
            val totalSize = response.headers()?.get(BuildConfig.TOTAL_RECORDS_HEADER)?.toInt()

                result = Page(
                        content = content ?: ArrayList(),
                        page = page,
                        size = content?.size ?: 0,
                        capacity = capacity,
                        collectionPages = totalPages,
                        collectionSize = totalSize
                )
        }

        return result
    }

    /**
     * Checks if the device can establish a connection to the internet.
     *
     * @return `true` if the device can connect to the Internet or `false` otherwise.
     */
    private fun canConnect(): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    // Methods to extract Retrofit.Call and Retrofit.Response attributes avoiding null pointer exceptions
    /**
     * Get the HTTP method from the [Call].
     *
     * This method is [NullPointerException] safe. It never raises a NullPointerException and either returns a null value.
     *
     * @param call Call.
     * @return Call HTTP method.
     */
    private fun method(call: Call<*>?): String {
        return call?.request()?.method() ?: ""
    }

    /**
     * Get the URL from the [Call].
     *
     * This method is [NullPointerException] safe. It never raises a NullPointerException and either returns a null value.
     *
     * @param call Call.
     * @return Call URL.
     */
    private fun url(call: Call<*>?): String {
        return call?.request()?.url()?.toString() ?: ""
    }

    /**
     * Get the HTTP status code from the [Response].
     *
     * This method is [NullPointerException] safe. It never raises a NullPointerException and either returns a null value.
     *
     * @param response Response.
     * @return Response HTTP status code.
     */
    private fun status(response: Response<*>?): String {
        return response?.code()?.toString() ?: ""
    }

    /**
     * Get the body from the [Response].
     *
     * This method is [NullPointerException] safe. It never raises a NullPointerException and either returns a null value.
     *
     * @param response Response.
     * @return Response body.
     */
    private fun body(response: Response<*>?): String {
        var body: String

        try {
            val gson = GsonBuilder().setPrettyPrinting().create()

            body = if (response != null) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        gson.toJson(response.body())
                    } else {
                        ""
                    }
                } else if (response.errorBody() != null) {
                    gson.toJson(response.errorBody())
                } else {
                    ""
                }
            } else {
                ""
            }
        } catch (e: IOException) {
            body = "{}"
        }

        return body
    }
}