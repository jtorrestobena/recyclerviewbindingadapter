package com.bytecoders.recyclerviewbindinglib.layoutmanager

import android.content.Context
import android.content.res.Resources
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import com.bytecoders.recyclerviewbindinglib.extensions.dpToPixels
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.sin

/**
 * Draws items on the screen showing an Arc
 */
class ArcLayoutManager(
    context: Context,
    private var horizontalOffset: Int = 0,
    widthDp: Float = 90F,
    heightDp: Float = 90F,
) : RecyclerView.LayoutManager() {

    private val displayWidth = Resources.getSystem().displayMetrics.widthPixels
    private val displayCenter = displayWidth / 2
    private var canScrollLeft = true
    private var canScrollRight = true

    private val viewWidth = widthDp.dpToPixels(context)
    private val viewHeight = heightDp.dpToPixels(context)

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
        RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)

    override fun canScrollHorizontally(): Boolean = true

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        if ((dx < 0 && !canScrollLeft) || (dx > 0 && !canScrollRight)) return 0
        horizontalOffset += dx
        fill(recycler, state)
        return dx
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)

        fill(recycler, state)
    }

    private fun fill(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        detachAndScrapAttachedViews(recycler ?: return)

        if (state == null || state.itemCount == 0) return

        canScrollLeft = true
        canScrollRight = true

        for (itemIndex in 0 until itemCount) {
            val left = (itemIndex * viewWidth) - horizontalOffset
            val right = left + viewWidth

            if (itemIndex == 0 && left > displayCenter) {
                canScrollLeft = false
            }

            if (itemIndex == (itemCount - 1) && right < displayCenter) {
                canScrollRight = false
            }

            if (right < 0) continue
            if (left > displayWidth) break

            val top = computeYComponent((left + right) / 2, viewHeight)
            val bottom = top.first + viewHeight

            val view = recycler.getViewForPosition(itemIndex)
            addView(view)

            val alpha = top.second
            view.rotation = (alpha * (180 / PI)).toFloat() - 90f

            measureChildWithMargins(view, viewWidth.toInt(), viewHeight.toInt())
            layoutDecoratedWithMargins(
                view,
                left.toInt(),
                top.first,
                right.toInt(),
                bottom.toInt()
            )
        }

        recycler.scrapList.toList().forEach {
            recycler.recycleView(it.itemView)
        }
    }

    private fun computeYComponent(
        viewCenterX: Float,
        h: Float
    ): Pair<Int, Double> {
        val radius = (h * h + displayCenter * displayCenter) / (h * 2)

        val xScreenFraction = viewCenterX.toDouble() / displayWidth.toDouble()
        val beta = acos(displayCenter / radius)

        val alpha = beta + (xScreenFraction * (Math.PI - (2 * beta)))
        val yComponent = radius - (radius * sin(alpha))

        return Pair(yComponent.toInt(), alpha)
    }
}