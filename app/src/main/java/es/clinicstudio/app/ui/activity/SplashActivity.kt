package es.clinicstudio.app.ui.activity

/**
 * Splash screen [android.app.Activity].
 *
 * @author vh @ recursividad.es
 */
class SplashActivity: BaseActivity() {

    override fun onStart() {
        super.onStart()

        // Navigate to the post list
        navigator.goToPostListScreen(this)
    }
}