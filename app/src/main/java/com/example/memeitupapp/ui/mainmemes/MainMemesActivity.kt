package com.example.memeitupapp.ui.mainmemes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.memeitupapp.R
import com.example.memeitupapp.ui.adapter.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.tabLayout
import kotlinx.android.synthetic.main.activity_main.view_pager

class MainMemesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPagerWithFragments()
    }

    private fun initViewPagerWithFragments() {
        val viewPager: ViewPager2 = view_pager
        viewPager.adapter = ViewPagerFragmentAdapter(supportFragmentManager, lifecycle)

        val tabLayout: TabLayout = tabLayout
        val listOfFragmentNames: List<String> = listOf(getString(R.string.grid_of_memes), getString(R.string.list_of_memes))
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = listOfFragmentNames[position]
        }.attach()
    }
}