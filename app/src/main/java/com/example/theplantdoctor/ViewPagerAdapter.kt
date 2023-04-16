package com.example.theplantdoctor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(val context: Context) : PagerAdapter() {

    var layoutInflater: LayoutInflater? = null

    //array of images, headers and descriptions
    val imgArray = arrayOf(
        R.drawable.first_slide,
        R.drawable.second_slide,
        R.drawable.third_slide,
        R.drawable.fourth_slide
    )

    val headerArray = arrayOf(
        "Quick solutions to your plant diseases",
        "Quick solutions to your plant diseases",
        "Quick solutions to your plant diseases",
        "Quick solutions to your plant diseases"
    )

    val descArray = arrayOf(
        "A user friendly UI to browse various treatments for your plants",
        "Lorem ipsum dolot sit amet, conseccuter, adpiscing incidunt ut laborrte eiusmud",
        "Lorem ipsum dolot sit amet, conseccuter, adpiscing incidunt ut laborrte eiusmud",
        "Lorem ipsum dolot sit amet, conseccuter, adpiscing incidunt ut laborrte eiusmud"
    )

    override fun getCount(): Int {
        return headerArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view == `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.slider, container, false)

        val img = view.findViewById<ImageView>(R.id.ivfirstslide)
        val header = view.findViewById<TextView>(R.id.tvheader1)
        val description = view.findViewById<TextView>(R.id.tvdesc1)

        img.setImageResource(imgArray[position])
        header.text = headerArray[position]
        description.text = descArray[position]

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}