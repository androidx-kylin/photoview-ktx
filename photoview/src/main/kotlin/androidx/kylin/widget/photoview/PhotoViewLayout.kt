package androidx.kylin.widget.photoview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper

/**
 * Layout for [PhotoView], provider drag event.
 *
 * @author RAE
 * @date 2024/09/25
 */
class PhotoViewLayout : FrameLayout {
    private val viewDragHelper: ViewDragHelper
    val photoView: PhotoView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        photoView = PhotoView(context, attrs)
        addView(photoView)
        viewDragHelper = ViewDragHelper.create(this, PhotoViewDragHelperCallback())
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (photoView.scale == 1.0f && ev.action == MotionEvent.ACTION_DOWN) {
            viewDragHelper.shouldInterceptTouchEvent(ev)
            return true
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (photoView.scale == 1.0f) {
            viewDragHelper.processTouchEvent(event)
            (parent as ViewGroup).requestDisallowInterceptTouchEvent(true)
            return true
        }
        return super.onTouchEvent(event)
    }

    private inner class PhotoViewDragHelperCallback : ViewDragHelper.Callback() {

        var capturedTop: Int = 0
        var capturedLeft: Int = 0

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            Log.d("rae", "tryCaptureView: $child")
            return child is PhotoView
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            super.onViewCaptured(capturedChild, activePointerId)
            capturedTop = capturedChild.top
            capturedLeft = capturedChild.left
            Log.d("rae", "onViewCaptured: ${capturedChild.top}, ${capturedChild.left}")
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            releasedChild.top = 0
            releasedChild.left = 0
            releasedChild.right = releasedChild.width
            ViewCompat.offsetTopAndBottom(releasedChild, 0)
            ViewCompat.offsetLeftAndRight(releasedChild, 0)
            Log.d(
                "rae",
                "onViewReleased: ${capturedTop}, ${capturedLeft}"
            )
        }
    }
}