package org.geeksforgeeks.demo

import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLL: LinearLayout
    private lateinit var adapter: SliderAdapter
    private lateinit var sliderModalArrayList: ArrayList<SliderModal>
    private lateinit var dots: Array<TextView?>
    private var size: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        viewPager = findViewById(R.id.idViewPager)
        dotsLL = findViewById(R.id.dotsLayout)

        // Create and populate slider items
        sliderModalArrayList = ArrayList()
        sliderModalArrayList.add(SliderModal("Slide 1", "Slide 1 heading", R.drawable.sample1))
        sliderModalArrayList.add(SliderModal("Slide 2", "Slide 2 heading", R.drawable.sample2))
        sliderModalArrayList.add(SliderModal("Slide 3", "Slide 3 heading", R.drawable.sample3))

        // Set up adapter with slider items
        adapter = SliderAdapter(this@MainActivity, sliderModalArrayList)
        viewPager.adapter = adapter

        // Get the total number of slides
        size = sliderModalArrayList.size

        // Add initial dot indicators
        addDots(size, 0)

        // Set page change listener
        viewPager.addOnPageChangeListener(viewListener)
    }

    // Method to add and update dot indicators
    private fun addDots(size: Int, pos: Int) {
        dots = arrayOfNulls(size)
        dotsLL.removeAllViews()

        for (i in 0 until size) {
            dots[i] = TextView(this).apply {
                text = Html.fromHtml("•")
                textSize = 35f
                setTextColor(ContextCompat.getColor(this@MainActivity, R.color.black))
            }
            dotsLL.addView(dots[i])
        }

        // Highlight selected dot
        if (dots.isNotEmpty()) {
            dots[pos]?.apply {
                setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
                textSize = 45f
            }
        }
    }

    // Page change listener to update dots on slide change
    private var viewListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            addDots(size, position)
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}