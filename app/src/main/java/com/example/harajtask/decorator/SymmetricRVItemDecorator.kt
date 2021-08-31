package com.example.harajtask.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * This class decorates the Recycler view in a Symmetric manner, also you have the choice to add a different top margin for the first item.
 */
class SymmetricRVItemDecorator(private val horizontalMargin: Int, private val verticalMargin: Int, private val firstItemTopMargin: Int = 0)
    : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildLayoutPosition(view);
        //set lateral margin to all
        outRect.right = horizontalMargin;
        outRect.left = horizontalMargin

        outRect.top = if (position == 0) {
            if (firstItemTopMargin == 0) verticalMargin else firstItemTopMargin
        } else verticalMargin

        // Set Bottom Margin to the last Item only
        if (position == state.itemCount - 1) outRect.bottom = verticalMargin

    }
}
