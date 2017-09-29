package es.clinicstudio.app.di

import javax.inject.Scope

/**
 * Scoping [Annotation] to allow dependencies whose lifetime should conform to
 * the life of the activity they belong to.
 *
 * @author vh @ recursividad.es
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope