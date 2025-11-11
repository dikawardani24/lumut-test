package dika.lumuttest.util

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


fun <T: Any> Single<T>.execute(
    onSuccess: (response: T) -> Unit,
    onError: (error: Throwable) -> Unit
): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableSingleObserver<T>() {
            override fun onSuccess(response: T) {
                onSuccess(response)
            }

            override fun onError(e: Throwable) {
                onError(e)
            }
        })
}

fun CompositeDisposable.stopIfRunning() {
    if (isDisposed) return
    dispose()
}