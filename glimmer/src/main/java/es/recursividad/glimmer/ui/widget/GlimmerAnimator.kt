package es.recursividad.glimmer.ui.widget

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import es.recursividad.glimmer.ui.utils.Gradient

/**
 * @author vh @ recursividad.es
 */
class GlimmerAnimator {

    private val startColor = Color.rgb(245, 245, 245)
    private val endColor = Color.rgb(238, 238, 238)

    private val animator = ValueAnimator.ofFloat(0f, 1f)
    private var glimmerDrawable: Drawable? = null

    private val attachedViews: MutableList<GlimmerView> = ArrayList()

    init {
        // Configure the animator
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.duration = 1500

        animator.addUpdateListener { animation ->
            // Create the Glimmer drawable
            glimmerDrawable = Gradient.getDrawable(startColor, endColor, (animation.animatedValue as Float).toDouble())

            // Apply the drawable to each view
            attachedViews.forEach({
                it.setBackgroundDrawable(glimmerDrawable)
            })
        }
    }

    /**
     * Get the default Glimmer [Drawable].
     *
     * @return Default Glimmer drawable.
     */
    fun getDrawable(): Drawable {
        return glimmerDrawable ?: Gradient.getDrawable(startColor, endColor, 0.toDouble())
    }

    /**
     * Attach view to the animator for being painted.
     *
     * @param[view] View to be attached to the animator.
     */
    fun attach(view: GlimmerView) {
        if (!attachedViews.contains(view)) {
            attachedViews.add(view)

            if (!animator.isRunning) {
                animator.start()
            }
        }
    }

    /**
     * Detach the view from the animator to stop from being painted.
     *
     * @param[view] View to be detached from the animator.
     */
    fun detach(view: GlimmerView) {
        attachedViews.remove(view)

        if (attachedViews.isEmpty()) {
            animator.cancel()
        }
    }
}