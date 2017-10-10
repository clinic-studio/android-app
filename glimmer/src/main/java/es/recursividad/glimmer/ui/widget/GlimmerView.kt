package es.recursividad.glimmer.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View


/**
 * @author vh @ recursividad.es
 */
class GlimmerView(context: Context, attrs: AttributeSet?): View(context, attrs) {

    init {
        // Set the initial background drawable
        setBackgroundDrawable(GlimmerAnimatorFactory.getInstance().getDrawable())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        // Attach the view to the animator
        GlimmerAnimatorFactory.getInstance().attach(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        // Detach the view from the animator
        GlimmerAnimatorFactory.getInstance().detach(this)
    }

    override fun onVisibilityChanged(changedView: View?, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)

        // Check visibility
        when (visibility) {
            // If the view is visible attach the view to the animator
            VISIBLE ->
                GlimmerAnimatorFactory.getInstance().attach(this)
            // If the view is not visible detach the view from the animator
            INVISIBLE, GONE ->
                GlimmerAnimatorFactory.getInstance().detach(this)
        }
    }
}