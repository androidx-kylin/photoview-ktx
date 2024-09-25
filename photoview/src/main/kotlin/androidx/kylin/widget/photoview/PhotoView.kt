package androidx.kylin.widget.photoview

import android.content.Context
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.view.GestureDetector
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatImageView

/**
 * A zoomable ImageView. See [PhotoViewAttacher] for most of the details on how the zooming
 * is accomplished
 */
@Suppress("unused")
open class PhotoView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    @StyleRes defStyle: Int = 0,
) : AppCompatImageView(context, attr, defStyle) {
    /**
     * Get the current [PhotoViewAttacher] for this view. Be wary of holding on to references
     * to this attacher, as it has a reference to this view, which, if a reference is held in the
     * wrong place, can cause memory leaks.
     *
     * This is a tricky way to check if the [attacher] has been initialized or not before
     * accessing to its methods.
     *
     * @return the attacher.
     */
    private val attacher: PhotoViewAttacher by lazy { PhotoViewAttacher(this) }
    private var pendingScaleType: ScaleType? = null

    init {
        super.setScaleType(ScaleType.MATRIX)
        // apply the previously applied scale type
        if (pendingScaleType != null) {
            scaleType = pendingScaleType!!
            pendingScaleType = null
        }
    }

    override fun getScaleType(): ScaleType {
        return attacher.scaleType
    }

    override fun getImageMatrix(): Matrix {
        return attacher.imageMatrix
    }

    override fun setOnLongClickListener(l: OnLongClickListener?) {
        attacher.setOnLongClickListener(l)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        attacher.setOnClickListener(l)
    }

    override fun setScaleType(scaleType: ScaleType) {
        if (drawable == null) {
            pendingScaleType = scaleType
        } else {
            attacher.scaleType = scaleType
        }
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        // setImageBitmap calls through to this method
        attacher.update()
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        attacher.update()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        attacher.update()
    }

    override fun setFrame(l: Int, t: Int, r: Int, b: Int): Boolean {
        val changed = super.setFrame(l, t, r, b)
        if (changed) {
            attacher.update()
        }
        return changed
    }

    fun setRotationTo(rotationDegree: Float) {
        attacher.setRotationTo(rotationDegree)
    }

    fun setRotationBy(rotationDegree: Float) {
        attacher.setRotationBy(rotationDegree)
    }

    var isZoomable: Boolean
        get() = attacher.isZoomable
        set(zoomable) {
            attacher.isZoomable = zoomable
        }
    val displayRect: RectF?
        get() = attacher.displayRect

    fun getDisplayMatrix(matrix: Matrix?) {
        attacher.getDisplayMatrix(matrix)
    }

    fun setDisplayMatrix(finalRectangle: Matrix?): Boolean {
        return attacher.setDisplayMatrix(finalRectangle)
    }

    fun getSuppMatrix(matrix: Matrix?) {
        attacher.getSuppMatrix(matrix)
    }

    fun setSuppMatrix(matrix: Matrix?): Boolean {
        return attacher.setDisplayMatrix(matrix)
    }

    var minimumScale: Float
        get() = attacher.minimumScale
        set(minimumScale) {
            attacher.minimumScale = minimumScale
        }
    var mediumScale: Float
        get() = attacher.mediumScale
        set(mediumScale) {
            attacher.mediumScale = mediumScale
        }
    var maximumScale: Float
        get() = attacher.maximumScale
        set(maximumScale) {
            attacher.maximumScale = maximumScale
        }
    var scale: Float
        get() = attacher.scale
        set(scale) {
            attacher.scale = scale
        }

    fun setAllowParentInterceptOnEdge(allow: Boolean) {
        attacher.setAllowParentInterceptOnEdge(allow)
    }

    fun setScaleLevels(minimumScale: Float, mediumScale: Float, maximumScale: Float) {
        attacher.setScaleLevels(minimumScale, mediumScale, maximumScale)
    }

    fun setOnMatrixChangeListener(listener: OnMatrixChangedListener?) {
        attacher.setOnMatrixChangeListener(listener)
    }

    fun setOnPhotoTapListener(listener: OnPhotoTapListener?) {
        attacher.setOnPhotoTapListener(listener)
    }

    fun setOnOutsidePhotoTapListener(listener: OnOutsidePhotoTapListener?) {
        attacher.setOnOutsidePhotoTapListener(listener)
    }

    fun setOnViewTapListener(listener: OnViewTapListener?) {
        attacher.setOnViewTapListener(listener)
    }

    fun setOnViewDragListener(listener: OnViewDragListener?) {
        attacher.setOnViewDragListener(listener)
    }

    fun setScale(scale: Float, animate: Boolean) {
        attacher.setScale(scale, animate)
    }

    fun setScale(scale: Float, focalX: Float, focalY: Float, animate: Boolean) {
        attacher.setScale(scale, focalX, focalY, animate)
    }

    fun setZoomTransitionDuration(milliseconds: Int) {
        attacher.setZoomTransitionDuration(milliseconds)
    }

    fun setOnDoubleTapListener(onDoubleTapListener: GestureDetector.OnDoubleTapListener?) {
        attacher.setOnDoubleTapListener(onDoubleTapListener)
    }

    fun setOnScaleChangeListener(onScaleChangedListener: OnScaleChangedListener?) {
        attacher.setOnScaleChangeListener(onScaleChangedListener)
    }

    fun setOnSingleFlingListener(onSingleFlingListener: OnSingleFlingListener?) {
        attacher.setOnSingleFlingListener(onSingleFlingListener)
    }
}
