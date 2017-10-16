package es.clinicstudio.app.ui.utils

import android.content.Context
import android.content.Intent
import es.clinicstudio.app.ui.activity.BaseActivity
import es.clinicstudio.app.ui.activity.HomeActivity
import es.clinicstudio.app.ui.activity.PostContentActivity

/**
 * Helper class for handling navigation through application activities.
 *
 * @author vh @ recursividad.es
 */
class Router(private val context: Context) {

    /**
     * Navigate to the home screen.
     *
     * @param[activity] Current activity from which navigate
     */
    fun goToHomeScreen(activity: BaseActivity) {
        val intent = HomeActivity.intent(context)
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