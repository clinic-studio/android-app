package es.recursividad.glimmer.ui.utils

import android.animation.ArgbEvaluator
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.support.annotation.ColorInt


/**
 * @author vh @ recursividad.es
 */
abstract class Gradient {

    companion object {

        fun getDrawable(@ColorInt start: Int, @ColorInt end: Int): Drawable {
            return getDrawable(start, end, 0.toDouble())
        }

        fun getDrawable(@ColorInt start: Int, @ColorInt end: Int, progress: Double): Drawable {
            var p = progress - Math.floor(progress)

            if (p <= 0.5) {
                p /= 0.5

                val evaluator = ArgbEvaluator()
                val startColor  = evaluator.evaluate((1 - p.toFloat()), start, end) as Int
                val middleColor = end
                val endColor    = evaluator.evaluate(p.toFloat(), start, end) as Int

                // Build shader
                val shaderFactory = object : ShapeDrawable.ShaderFactory() {

                    /**
                     * Returns the Shader to be drawn when a Drawable is drawn. The dimensions of the
                     * Drawable are passed because they may be needed to adjust how the Shader is configured
                     * for drawing. This is called by ShapeDrawable.setShape().
                     *
                     * @param width the width of the Drawable being drawn
                     * @param height the height of the Drawable being drawn
                     * @return the Shader to be drawn
                     */
                    override fun resize(width: Int, height: Int): Shader {
                        return LinearGradient(
                                // Position
                                0.toFloat(), 0.toFloat(), width.toFloat(), 0.toFloat(),
                                // Gradient
                                intArrayOf(startColor, middleColor, endColor), floatArrayOf(0f, p.toFloat(), 1f), Shader.TileMode.CLAMP)
                    }
                }

                // Prepare background
                val drawable = PaintDrawable()

                drawable.shape = RectShape()
                drawable.shaderFactory = shaderFactory

                return drawable
            } else {
                return getDrawable(end, start, (p - 0.5))
            }
        }
    }
}