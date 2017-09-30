package es.clinicstudio.app.domain.error

/**
 * Application exception that might be thrown when there were a problem when communicating with the Internet.
 *
 * @author vh @ recursividad.es
 */
class CommunicationException(message: String = "Sorry, but some error occurred when trying to connect with the Internet", cause: Throwable? = null) : ApplicationException(message, cause)