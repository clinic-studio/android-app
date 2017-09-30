package es.clinicstudio.app.domain.error

/**
 * Application exception that might be thrown when a method receives an invalid argument.
 *
 * @author vh @ recursividad.es
 */
class IllegalArgumentException(message: String = "Sorry, but it seems that something didn't work", cause: Throwable? = null) : ApplicationException(message, cause) {
}