package com.example.memeitupapp.util

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class CubeOutTransformer : ViewPager2.PageTransformer {
    private val distanceMultiplier: Int = TWENTY

    override fun transformPage(page: View, position: Float) {
        page.cameraDistance = (page.width * distanceMultiplier).toFloat()
        page.pivotX = if (position < 0f) page.width.toFloat() else 0f
        page.pivotY = page.height * 0.5f
        page.rotationY = 90f * position
    }
}