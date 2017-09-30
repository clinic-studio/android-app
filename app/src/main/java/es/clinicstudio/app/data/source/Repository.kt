package es.clinicstudio.app.data.source

/**
 * @author vh @ recursividad.es
 */

import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.GsonBuilder
import es.clinicstudio.app.domain.error.CommunicationException
import es.clinicstudio.app.domain.error.NetworkUnreachableException
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


/**
 * Base repository class with some useful and common methods already implemented.
 *
 * @author victor.hernandezbermejo @ gmail.com
 */
abstract class Repository(private val connectivityManager: ConnectivityManager, private val loggingTag: String) {

    /**
     * Fetch one entity invoking the specified [Call].
     *
     * @param <T> Type of the entity to retrieve.
     * @param call API call.
     * @return Returns the entity retrieved when executing the call.
     * @throws IllegalArgumentException Thrown if some invalid argument is passed to the method.
     * @throws NetworkUnreachableException Thrown if the device cannot establish a connection to the internet.
     * @throws CommunicationException Thrown if a communication problem occurred when executing the call.
    </T> */
    @Throws(IllegalArgumentException::class, NetworkUnreachableException::class, CommunicationException::class)
    protected fun <T> fetchEntity(call: Call<T>?): T? {
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

        var result: T? = null
        try {
            // Execute the API call
            val response = call.execute()

            // Verify that it was possible to connect to the API and obtain a response
            if (response == null) {
                Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'. Null response", method(call), url(call)))
                throw CommunicationException()
            }

            // Verify that the response was successful
            if (response.isSuccessful) {
                Log.v(loggingTag, String.format("REST API :: Success when calling '%s %s'. The response was:\nStatus: %s\n\n%s\n", method(call), url(call), status(response), body(response)))
                result = response.body()
            } else {
                Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'. The response was:\nStatus: %s\n\n%s\n", method(call), url(call), status(response), body(response)))
            }

        } catch (e: IOException) {
            Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'.", method(call), url(call)), e)
            throw CommunicationException(cause = e)
        }

        return result
    }

    /**
     * Fetch a list of entities invoking the specified [Call].
     *
     * @param <T> Type of the entities to retrieve.
     * @param call API call.
     * @return Returns a list of entities retrieved when executing the call.
     * @throws IllegalArgumentException Thrown if some invalid argument is passed to the method.
     * @throws NetworkUnreachableException Thrown if the device cannot establish a connection to the internet.
     * @throws CommunicationException Thrown if a communication problem occurred when executing the call.
    </T> */
    @Throws(IllegalArgumentException::class, NetworkUnreachableException::class, CommunicationException::class)
    protected fun <T> fetchEntities(call: Call<List<T>>?): List<T>? {
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

        var result: List<T>? = null
        try {
            // Execute the API call
            val response = call.execute()

            // Verify that it was possible to connect to the API and obtain a response
            if (response == null) {
                Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'. Null response", method(call), url(call)))
                throw CommunicationException()
            }

            // Verify that the response was successful
            if (response.isSuccessful) {
                Log.v(loggingTag, String.format("REST API :: Success when calling '%s %s'. The response was:\nStatus: %s\n\n%s\n", method(call), url(call), status(response), body(response)))
                result = response.body()
            } else {
                Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'. The response was:\nStatus: %s\n\n%s\n", method(call), url(call), status(response), body(response)))
            }

        } catch (e: IOException) {
            Log.e(loggingTag, String.format("REST API :: Some error occurred when calling '%s %s'.", method(call), url(call)), e)
            throw CommunicationException(cause = e)
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