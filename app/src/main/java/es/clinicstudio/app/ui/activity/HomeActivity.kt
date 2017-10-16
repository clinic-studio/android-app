package es.clinicstudio.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import es.clinicstudio.app.R
import es.clinicstudio.app.ui.fragment.PostListFragment

/**
 * @author vh @ recursividad.es
 */
class HomeActivity : BaseActivity() {

    companion object {

        fun intent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Add the post list fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, PostListFragment())
                .commit()
    }
}