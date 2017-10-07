package es.clinicstudio.app.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Base class fore every use case interactor declared in the app.
 *
 * @param[T] Return type of the use case.
 * @param[P] Parameters for the use case execution.
 *
 * @author vh @ recursividad.es
 */
abstract class UseCase<T, in P>(
        private val executionScheduler: Scheduler = Schedulers.io(),
        private val observerScheduler: Scheduler = AndroidSchedulers.mainThread()
) {

    /**
     * Creates an observable ready to execute this use case with the given parameters.
     *
     * @param[params] Parameters for the use case execution.
     * @return Observable ready to execute this use case with the specified parameters.
     */
    abstract fun buildObservable(params: P?): Observable<T>

    /**
     * Execute the use case.
     *
     * @param[observer] [DisposableObserver] that will be notified with the result of the use case execution.
     * @param[params] Parameters for the execution of the use case.
     */
    fun execute(observer: DisposableObserver<T>, params: P? = null): Disposable {
        // Build the observable and execute it
        return buildObservable(params)
                .subscribeOn(executionScheduler)
                .observeOn(observerScheduler)
                .subscribeWith(observer)
    }
}