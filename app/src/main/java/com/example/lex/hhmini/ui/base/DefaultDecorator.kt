package com.example.lex.hhmini.ui.base

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.lex.hhmini.R
import javax.inject.Inject


class DefaultDecorator @Inject constructor(context: Context) : RecyclerView.ItemDecoration() {
    val space: Int

    init {
        space = context.resources.getDimensionPixelSize(R.dimen.list_item_spaces)
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.set(space, 0, space, space)
    }
}
