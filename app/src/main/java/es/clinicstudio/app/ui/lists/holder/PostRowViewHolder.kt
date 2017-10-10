package es.clinicstudio.app.ui.lists.holder

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindColor
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import es.clinicstudio.app.R
import es.clinicstudio.app.domain.entity.ImageUrlResource
import es.clinicstudio.app.domain.entity.Post
import es.clinicstudio.app.ui.utils.StringUtils
import es.recursividad.glimmer.ui.widget.GlimmerView
import jp.wasabeef.blurry.Blurry


/**
 * @author vh @ recursividad.es
 */
class PostRowViewHolder(view: View, onItemClickedListener: OnItemClickedListener<Post>? = null): RowViewHolder<Post>(view, onItemClickedListener) {

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

    @BindView(R.id.postCategoryPlaceholder)
    lateinit var postCategoryPlaceholder: GlimmerView

    @BindView(R.id.postTitlePlaceholder)
    lateinit var postTitlePlaceholder: GlimmerView

    @BindView(R.id.postAuthorPlaceholder)
    lateinit var postAuthorPlaceholder: GlimmerView

    @BindView(R.id.postPublishDatePlaceholder)
    lateinit var postPublishDatePlaceholder: GlimmerView


    @BindColor(R.color.material_gray100_alpha50) @JvmField @ColorInt
    var materialGray100Alpha50Color: Int = 0


    private var glideRequest: Request? = null

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

        //
        // Hide placeholders
        //
        postCategoryPlaceholder.visibility = View.GONE
        postTitlePlaceholder.visibility = View.GONE
        postAuthorPlaceholder.visibility = View.GONE
        postPublishDatePlaceholder.visibility = View.GONE


        //
        // Remove content
        //
        postMainPictureImageView.setImageDrawable(null)
        postBlurBackgroundImageView.setImageDrawable(null)
        postCategoryTextView.text = null
        postTitleTextView.text = null
        postAuthorTextView.text = null
        postPublishDateTextView.text = null

        //
        // Clear the Glide request in case it hasn't finished yet
        //
        glideRequest?.clear()
    }

    /**
     * Get the content hold in this row.
     *
     * @return Content hold by the view.
     */
    override fun getContent(): Post? {
        return content
    }

    /**
     * Set the content of the row item.
     *
     * @param content Content to be displayed.
     */
    override fun setContent(content: Post) {
        this.content = content

        //
        // Hide placeholders
        //
        postCategoryPlaceholder.visibility = View.GONE
        postTitlePlaceholder.visibility = View.GONE
        postAuthorPlaceholder.visibility = View.GONE
        postPublishDatePlaceholder.visibility = View.GONE


        //
        // Display content
        //
        // Category
        if (content.embedded.terms.isNotEmpty() && content.embedded.terms[0].isNotEmpty()) {
            postCategoryTextView.text = content.embedded.terms[0][0].name
        }

        // Title
        postTitleTextView.text = content.title.rendered

        // Author
        if (content.embedded.author.size == 1) {
            postAuthorTextView.text = context?.getString(R.string.by_author, content.embedded.author[0].name)
        }
        else if (content.embedded.author.size > 1) {
            postAuthorTextView.text = context?.getString(R.string.by_various_authors)
        }

        // Date
        postPublishDateTextView.text = StringUtils().getDateString(content.date, postPublishDateTextView.context)

        // Background image
        val imageUrls = ImageUrlResource.from(content.content.rendered)?.get(0)
        if (imageUrls != null) {
            glideRequest = Glide.with(context)
                    .load(imageUrls.getBestFit(context?.resources?.displayMetrics?.widthPixels ?: 0))
                    .listener(
                            object : RequestListener<Drawable> {
                                override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                    if (resource != null && resource is BitmapDrawable) {
                                        val bitmap = resource.bitmap

                                        val ratio = bitmap.width / postMainPictureImageView.width.toDouble()
                                        val height = (postBlurBackgroundImageView.height * ratio).toInt()
                                        val width = (postBlurBackgroundImageView.width * ratio).toInt()

                                        if (height > 0 && width > 0) {
                                            val blurryBitmap = Bitmap.createBitmap(bitmap, 0, bitmap.height - height, width, height)
                                            materialGray100Alpha50Color?.let { Blurry.with(context).radius(10).sampling(1).color(it).from(blurryBitmap).into(postBlurBackgroundImageView) }
                                        }
                                    }

                                    return false
                                }

                                override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                    return false
                                }
                            })
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(postMainPictureImageView)
                    .request
        }
    }

    override fun placeholder() {
        postCategoryPlaceholder.visibility = View.VISIBLE
        postTitlePlaceholder.visibility = View.VISIBLE
        postAuthorPlaceholder.visibility = View.VISIBLE
        postPublishDatePlaceholder.visibility = View.VISIBLE
    }
}