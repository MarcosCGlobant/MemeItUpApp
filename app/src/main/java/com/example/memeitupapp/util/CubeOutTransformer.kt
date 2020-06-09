package com.example.memeitupapp.util

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.globant.domain.util.TWENTY

class CubeOutTransformer : ViewPager2.PageTransformer {
    private val distanceMultiplier: Int = TWENTY

    override fun transformPage(page: View, position: Float) {
        page.cameraDistance = (page.width * distanceMultiplier).toFloat()
        page.pivotX = if (position < ZERO_FLOAT) page.width.toFloat() else ZERO_FLOAT
        page.pivotY = page.height * ZERO_POINT_FIVE_FLOAT
        page.rotationY = NINETY_FLOAT * position
    }

    companion object {
        const val ZERO_FLOAT = 0f
        const val NINETY_FLOAT = 90f
        const val ZERO_POINT_FIVE_FLOAT = 0.5f
    }
}