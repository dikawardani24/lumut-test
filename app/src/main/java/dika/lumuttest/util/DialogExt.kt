package dika.lumuttest.util

import android.content.Context
import dika.lumuttest.ui.dialog.LoadingDialog

private var loadingDialog: LoadingDialog? = null

fun Context.showLoading(message: String = "Loading data...") {
    if (loadingDialog == null) {
        loadingDialog = LoadingDialog(this, message)
    }
    loadingDialog?.show()
}

fun hideLoading() {
    val isLoadingShow = loadingDialog?.isShowing ?: false
    if (!isLoadingShow) return
    loadingDialog?.dismiss()
    loadingDialog = null
}