package es.clinicstudio.app.domain.interactor

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

/**
 * [io.reactivex.Observer] with default no-operation for [onNext], [onError] and
 * [onComplete] methods.
 *
 * @author vh @ recursividad.es
 */
open class DefaultObserver<T> : DisposableObserver<T>() {


    /**
     * Provides the Observer with a new item to observe.
     *
     * The [Observable] may call this method zero or more times.
     *
     * The `Observable` will not call this method again after it calls either [onComplete] or
     * [onError].
     *
     * @param t the item emitted by the Observable
     */
    override fun onNext(t: T) {
        // no-op by default
    }

    /**
     * Notifies the Observer that the [Observable] has experienced an error condition.
     *
     * If the [Observable] calls this method, it will not thereafter call [onNext] or
     * [onComplete].
     *
     * @param e the exception encountered by the Observable
     */
    override fun onError(e: Throwable) {
        // no-op by default
    }

    /**
     * Notifies the Observer that the [Observable] has finished sending push-based notifications.
     *
     * The [Observable] will not call this method if it calls [onError].
     */
    override fun onComplete() {
        // no-op by default
    }
}