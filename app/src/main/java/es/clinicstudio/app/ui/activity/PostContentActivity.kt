package es.clinicstudio.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import es.clinicstudio.app.R
import kotlinx.android.synthetic.main.activity_post_content.*

/**
 * @author vh @ recursividad.es
 */
class PostContentActivity: BaseActivity() {

    companion object {
        private val EXTRA_POST_TITLE = "extras.title"
        private val EXTRA_POST_HTML_CONTENT = "extras.content"

        fun intent(context: Context, title: String, htmlContent: String): Intent {
            val intent = Intent(context, PostContentActivity::class.java)
            intent.putExtra(EXTRA_POST_TITLE, title)
            intent.putExtra(EXTRA_POST_HTML_CONTENT, htmlContent)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_content)

        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Load the content
        val title = intent.extras.getString(EXTRA_POST_TITLE)
        val htmlContent = intent.extras.getString(EXTRA_POST_HTML_CONTENT)

        setTitle(title)
        postContentWebView.loadData("<link rel=\"stylesheet\" id=\"hestia_style-css\" href=\"http://clinicstudio.es/wp-content/themes/hestia/style.css?ver=1.1.50\" type=\"text/css\" media=\"all\"><body style=\"background-color:white\">$htmlContent</body>", "text/html", "utf-8")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}