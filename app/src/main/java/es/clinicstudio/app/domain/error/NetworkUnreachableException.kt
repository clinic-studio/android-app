package es.clinicstudio.app.domain.error

/**
 * Application exception that might be thrown when the application cannot establish a connection
 * to the internet.
 *
 * @author vh @ recursividad.es
 */
class NetworkUnreachableException(message: String = "Sorry, but we cannot connect with the Internet. Check your connection and try it again", cause: Throwable? = null) : ApplicationException(message, cause)