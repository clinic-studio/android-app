package es.clinicstudio.app.ui.utils

import android.content.Context
import android.content.Intent
import es.clinicstudio.app.ui.activity.BaseActivity
import es.clinicstudio.app.ui.activity.PostContentActivity
import es.clinicstudio.app.ui.activity.PostListActivity

/**
 * Helper class for handling navigation through application activities.
 *
 * @author vh @ recursividad.es
 */
class Router(private val context: Context) {

    /**
     * Navigate to the post list screen.
     *
     * @param[activity] Current activity from which navigate
     */
    fun goToPostListScreen(activity: BaseActivity) {
        val intent = Intent(context, PostListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

        activity.startActivity(intent)
        activity.finish()
    }

    /**
     * Navigate to the post content screen.
     *
     * @param[activity] Current activity from which navigate
     */
    fun goToPostContentScreen(activity: BaseActivity, title: String, htmlContent: String) {
        activity.startActivity(PostContentActivity.intent(context, title, htmlContent))
    }
}