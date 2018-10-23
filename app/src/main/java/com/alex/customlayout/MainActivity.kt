package com.alex.customlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var delay = 300L
        val handler = Handler()
        Thread {
            for (i in 1..20) {
                Thread.sleep(delay)
                handler.post {
                    when(i){
                        1-> ivFirst.visibility = View.GONE
                        2-> ivSecond.visibility = View.GONE
                        3-> ivThird.visibility = View.GONE
                        4-> ivFourth.visibility = View.GONE
                        5-> ivFifth.visibility = View.GONE

                        6-> ivFirst.visibility = View.VISIBLE
                        7-> ivSecond.visibility = View.VISIBLE
                        8-> ivThird.visibility = View.VISIBLE
                        9-> ivFourth.visibility = View.VISIBLE
                        10-> ivFifth.visibility = View.VISIBLE.apply { delay = 1000L }

                        11-> customLayout.removeView(ivFirst)
                        12-> customLayout.removeView(ivSecond)
                        13-> customLayout.removeView(ivThird)
                        14-> customLayout.removeView(ivFourth)
                        15-> customLayout.removeView(ivFifth)

                        16-> customLayout.addView(ivFirst)
                        17-> customLayout.addView(ivSecond)
                        18-> customLayout.addView(ivThird)
                        19-> customLayout.addView(ivFourth)
                        20-> customLayout.addView(ivFifth)
                    }
                }
            }
        }.start()
    }
}
