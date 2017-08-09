package com.example.lex.hhmini.presentation.base

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.lex.hhmini.R
import javax.inject.Inject


class DefaultDecorator(context: Context, val orientation: Int) : DividerItemDecoration(context, orientation) {
    val space: Int = context.resources.getDimensionPixelSize(R.dimen.list_item_spaces)

    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        val pos = parent.getChildAdapterPosition(view)
        if (orientation == VERTICAL && pos == 0) outRect.top = space
    }

}
