package es.recursividad.glimmer.ui.widget

/**
 * @author vh @ recursividad.es
 */
abstract class GlimmerAnimatorFactory {

    companion object {
        // Glimmer animator
        private var animator: GlimmerAnimator? = null

        /**
         * Get the [GlimmerAnimator] instance.
         *
         * @return Glimmer animator instance
         */
        fun getInstance(): GlimmerAnimator {
            if (animator == null) {
                animator = GlimmerAnimator()
            }

            return animator as GlimmerAnimator
        }
    }
}