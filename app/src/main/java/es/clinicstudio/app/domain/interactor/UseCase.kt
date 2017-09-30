package es.clinicstudio.app.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * @author vh @ recursividad.es
 */
abstract class UseCase<T, P>(execution: Scheduler?, observer: Scheduler?) {

    private val executionScheduler: Scheduler? = execution
        get() = field ?: Schedulers.io()

    private val observerScheduler: Scheduler? = observer
        get() = field ?: AndroidSchedulers.mainThread()

    /**
     * Creates an observable ready to execute this use case with the given parameters.
     *
     * @param params Parameters for the use case execution.
     * @return Observable ready to execute this use case with the specified parameters.
     */
    abstract fun buildObservable(params: P): Observable<T>

    /**
     * Execute the use case.
     *
     * @param observer [DisposableObserver] that will be notified with the result of the use case execution.
     * @param params Parameters for the execution of the use case.
     */
    fun execute(observer: DisposableObserver<T>, params: P): Disposable {
        // Build the observable and execute it
        return buildObservable(params)
                .subscribeOn(executionScheduler)
                .observeOn(observerScheduler)
                .subscribeWith(observer)
    }
}