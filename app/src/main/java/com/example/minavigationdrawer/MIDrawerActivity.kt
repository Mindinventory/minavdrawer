package com.example.minavigationdrawer

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.mindinventory.midrawer.MIDrawerView
import com.mindinventory.midrawer.MIDrawerView.Companion.MI_TYPE_DOOR_IN
import com.mindinventory.midrawer.MIDrawerView.Companion.MI_TYPE_DOOR_OUT
import com.mindinventory.midrawer.MIDrawerView.Companion.MI_TYPE_SLIDE
import com.mindinventory.midrawer.MIDrawerView.Companion.MI_TYPE_SLIDE_WITH_CONTENT

class MIDrawerActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "MIDrawerActivity"
    private var slideType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set color for the container's content as transparent
        drawer_layout.setScrimColor(Color.TRANSPARENT)

        nav_scroll.setOnClickListener(this)
        nav_slide.setOnClickListener(this)
        nav_doorIn.setOnClickListener(this)
        nav_doorOut.setOnClickListener(this)

        setSupportActionBar(toolbar)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_action_name)

        // Implement the drawer listener
        drawer_layout.setMIDrawerListener(object : MIDrawerView.MIDrawerEvents {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                Log.d(TAG, "Drawer Opened")
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                Log.d(TAG, "Drawer closed")
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.nav_scroll -> {
                avoidDoubleClicks(nav_scroll)
                slideType = MI_TYPE_SLIDE_WITH_CONTENT
                updateSliderTypeEvents()
            }
            R.id.nav_slide -> {
                avoidDoubleClicks(nav_slide)
                slideType = MI_TYPE_SLIDE
                updateSliderTypeEvents()
            }
            R.id.nav_doorIn -> {
                avoidDoubleClicks(nav_doorIn)
                slideType = MI_TYPE_DOOR_IN
                updateSliderTypeEvents()
            }
            R.id.nav_doorOut -> {
                avoidDoubleClicks(nav_doorIn)
                slideType = MI_TYPE_DOOR_OUT
                updateSliderTypeEvents()
            }
        }
    }

    private fun updateSliderTypeEvents() {
        if (handler == null) {
            handler = Handler()
            drawer_layout.closeDrawer(GravityCompat.START)
            handler?.postDelayed(runnable, 500)
        }
    }

    var handler: Handler? = null
    var runnable: Runnable = Runnable {
        when (slideType) {
            MI_TYPE_SLIDE_WITH_CONTENT -> {
                toolbar.title = this@MIDrawerActivity.resources.getString(R.string.scroll)
            }
            MI_TYPE_SLIDE -> {
                toolbar.title = this@MIDrawerActivity.resources.getString(R.string.slide)
            }
            MI_TYPE_DOOR_IN -> {
                toolbar.title = this@MIDrawerActivity.resources.getString(R.string.doorIn)
            }
            MI_TYPE_DOOR_OUT -> {
                toolbar.title = this@MIDrawerActivity.resources.getString(R.string.doorOut)
            }
        }
        drawer_layout.setSliderType(slideType)
        handler = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Avoid double click.
     */
    fun avoidDoubleClicks(view: View) {
        val DELAY_IN_MS: Long = 900
        if (!view.isClickable) {
            return
        }
        view.isClickable = false
        view.postDelayed({ view.isClickable = true }, DELAY_IN_MS)
    }

}
