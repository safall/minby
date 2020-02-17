package com.afi.minby.core

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import com.afi.minby.R
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.android.MainThreadDisposable
import io.reactivex.subjects.PublishSubject

@MainThread
fun View.clickObservable(): Observable<View> {
    MainThreadDisposable.verifyMainThread()

    val subject = PublishSubject.create<View>()
    setOnClickListener {
        subject.onNext(it)
    }
    return subject
}

fun View.getViewLocationY(): Int {
    val viewLocation = IntArray(2)
    getLocationInWindow(viewLocation)
    return viewLocation[1]
}

fun View.showErrorSnackbar(@StringRes message: Int, length: Int = Snackbar.LENGTH_LONG) {
    makeError(this, message, length)?.show()
}

fun makeError(view: View?, @StringRes message: Int, length: Int): Snackbar? {
    val snackbar = Snackbar.make(view!!, message, length)
    setBackgroundColor(snackbar, R.color.colorPrimaryDark)
    return snackbar
}

fun setBackgroundColor(snackbar: Snackbar, @ColorRes colorResId: Int) {
    snackbar.view.setBackgroundColor(snackbar.view.context.getColor(colorResId))
}

fun View.hideKeyboardAndClearFocus() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

    inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)

    post { clearFocus() }
}

fun View.showKeyboardAndRequestFocus() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

    inputMethodManager?.showSoftInput(this, 0)
    inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

    requestFocus()
}
