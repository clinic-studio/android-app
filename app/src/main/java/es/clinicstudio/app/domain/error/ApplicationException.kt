package es.clinicstudio.app.domain.error

/**
 * Base class for any exception occurred withing the Android app.
 *
 * @author vh @ recursividad.es
 */
open class ApplicationException(override val message: String, override val cause: Throwable?) : Exception()