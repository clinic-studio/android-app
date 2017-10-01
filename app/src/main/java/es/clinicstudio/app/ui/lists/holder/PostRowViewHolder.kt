package es.clinicstudio.app.ui.lists.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import es.clinicstudio.app.R
import es.clinicstudio.app.domain.entity.Post

/**
 * @author vh @ recursividad.es
 */
class PostRowViewHolder(view: View): RowViewHolder<Post>(view) {

    private var content: Post? = null
    private var context: Context? = null

    @BindView(R.id.postMainPictureImageView)
    lateinit var postMainPictureImageView: ImageView

    @BindView(R.id.postBlurBackgroundImageView)
    lateinit var postBlurBackgroundImageView: ImageView

    @BindView(R.id.postCategoryTextView)
    lateinit var postCategoryTextView: TextView

    @BindView(R.id.postTitleTextView)
    lateinit var postTitleTextView: TextView

    @BindView(R.id.postAuthorTextView)
    lateinit var postAuthorTextView: TextView

    @BindView(R.id.postPublishDateTextView)
    lateinit var postPublishDateTextView: TextView


    // Initialize the row view holder
    init {
        // Bind views
        ButterKnife.bind(this, itemView)
        context = itemView.context
    }


    /**
     * Recycle the view for further uses.
     */
    override fun recycle() {
        content = null

        postMainPictureImageView.setImageDrawable(null)
        postBlurBackgroundImageView.setImageDrawable(null)
        postCategoryTextView.text = null
        postTitleTextView.text = null
        postAuthorTextView.text = null
        postPublishDateTextView.text = null
    }

    /**
     * Set the content of the row item.
     *
     * @param content Content to be displayed.
     */
    override fun setContent(content: Post) {
        this.content = content

        postTitleTextView.text = content.title.rendered
    }
}