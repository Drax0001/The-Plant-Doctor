package com.example.theplantdoctor

import android.annotation.SuppressLint
import android.content.Intent
//import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
//import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class Onboarding : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportActionBar?.hide()

//        val relativeLayout = findViewById<RelativeLayout>(R.id.clmain)
//        val animationDrawable = relativeLayout.background as AnimationDrawable
        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        val btn = findViewById<Button>(R.id.btn)
        val btnSkip = findViewById<Button>(R.id.btn_skip_intro)

//        animationDrawable.setEnterFadeDuration(2000)
//        animationDrawable.setExitFadeDuration(2000)
//        animationDrawable.start()

        viewPager = findViewById(R.id.vpslide)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        dotsIndicator.attachTo(viewPager)

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //bunch of code
            }

            override fun onPageSelected(position: Int) {
                Log.i("Tag", position.toString())
//                if(position == 0) {
//                    relativeLayout.setBackgroundResource(R.drawable.gradient_animation)
//                    val animationDrawable = relativeLayout.background as AnimationDrawable
//                    animationDrawable.setEnterFadeDuration(2000)
//                    animationDrawable.setExitFadeDuration(2000)
//                    animationDrawable.start()
//                }
//                if(position == 1) {
//                    relativeLayout.setBackgroundResource(R.drawable.gradient_animation)
//                    val animationDrawable = relativeLayout.background as AnimationDrawable
//                    animationDrawable.setEnterFadeDuration(2000)
//                    animationDrawable.setExitFadeDuration(2000)
//                    animationDrawable.start()
//                }
//                if(position == 2) {
//                    relativeLayout.setBackgroundResource(R.drawable.gradient_animation)
//                    val animationDrawable = relativeLayout.background as AnimationDrawable
//                    animationDrawable.setEnterFadeDuration(2000)
//                    animationDrawable.setExitFadeDuration(2000)
//                    animationDrawable.start()
//                }
                if(position == 4) {
//                    relativeLayout.setBackgroundResource(R.drawable.gradient_animation)
//                    val animationDrawable = relativeLayout.background as AnimationDrawable
//                    animationDrawable.setEnterFadeDuration(2000)
//                    animationDrawable.setExitFadeDuration(2000)
//                    animationDrawable.start()
                    btn.visibility = View.VISIBLE
                    btnSkip.visibility = View.GONE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        btn.setOnClickListener {
            val intent = Intent(this@Onboarding, GetStarted::class.java)
            startActivity(intent)
            finish()
        }

        btnSkip.setOnClickListener {
            val intent = Intent(this@Onboarding, Homepage::class.java)
            startActivity(intent)
            finish()
        }
    }
}