package org.geeksforgeeks.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class SliderAdapter(
    private var context: Context,
    private var sliderModalArrayList: ArrayList<SliderModal>
) : PagerAdapter() {

    // LayoutInflater to inflate custom layout
    private lateinit var layoutInflater: LayoutInflater

    // Returns the total number of items
    override fun getCount(): Int {
        return sliderModalArrayList.size
    }

    // Checks whether a page View is associated with a specific key object
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    // Instantiates and returns a page View object
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.slider_layout, container, false)

        // Initialize views
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val titleTV = view.findViewById<TextView>(R.id.titleTextView)
        val headingTV = view.findViewById<TextView>(R.id.descriptionTextView)

        // Set data to views
        val modal: SliderModal = sliderModalArrayList[position]
        titleTV.text = modal.title
        headingTV.text = modal.heading
        Glide.with(context).load(modal.imgUrl).into(imageView)

        // Add the view to container
        container.addView(view)
        return view
    }

    // Removes a page View from the container
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}