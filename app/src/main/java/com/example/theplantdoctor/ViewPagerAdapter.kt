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
import com.airbnb.lottie.LottieAnimationView

class ViewPagerAdapter(val context: Context) : PagerAdapter() {

    var layoutInflater: LayoutInflater? = null

    //array of images, headers and descriptions
    val imgArray = arrayOf(
        R.drawable.third_slide,
        R.drawable.doctors_slide,
        R.drawable.fourth_slide,
        R.drawable.first_slide,
        R.drawable.second_slide
    )

    val headerArray = arrayOf(
        "Welcome to the plant Doctor",
        "Doctors Available to Chat",
        "Plant Care Tips",
        "Plant Disease Encyclopedia",
        "AI Chat Support"
    )

    val descArray = arrayOf(
        "",
        "Talk to a certified plant doctor for expert solutions to your plant problems",
        "Get the most out of your plants with our personalized care recommendations",
        "Identify and treat any plant disease with our encyclopedia of symptoms and solutions",
        "Our AI chat support is available 24/7 to help you with your plant problems"
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