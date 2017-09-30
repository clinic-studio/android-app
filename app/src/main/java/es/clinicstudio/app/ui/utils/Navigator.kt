package es.clinicstudio.app.ui.utils

import android.content.Context
import android.content.Intent
import es.clinicstudio.app.ui.activity.BaseActivity
import es.clinicstudio.app.ui.activity.PostListActivity

/**
 * Helper class for handling navigation through application activities.
 *
 * @author vh @ recursividad.es
 */
class Navigator(private val context: Context) {

    fun goToPostListScreen(activity: BaseActivity) {
        var intent = Intent(context, PostListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

        activity.startActivity(intent)
        activity.finish()
    }
}