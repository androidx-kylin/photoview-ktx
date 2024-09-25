package androidx.kylin.widget.photoview

import android.view.View

internal object Compat {

    private const val SIXTY_FPS_INTERVAL = 1000 / 60

    @JvmStatic
    fun postOnAnimation(view: View, runnable: Runnable) {
        view.postDelayed(runnable, SIXTY_FPS_INTERVAL.toLong())
    }
}
