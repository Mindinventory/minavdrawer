package com.example.minavigationdrawer

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.minavigationdrawer.databinding.ActivityMainBinding
import com.mindinventory.midrawer.MIDrawerView
import com.mindinventory.midrawer.MIDrawerView.Companion.MI_TYPE_DOOR_IN
import com.mindinventory.midrawer.MIDrawerView.Companion.MI_TYPE_DOOR_OUT
import com.mindinventory.midrawer.MIDrawerView.Companion.MI_TYPE_SLIDE
import com.mindinventory.midrawer.MIDrawerView.Companion.MI_TYPE_SLIDE_WITH_CONTENT

class MIDrawerActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "MIDrawerActivity"
    private var slideType = 0
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // Set color for the container's content as transparent
        activityMainBinding.drawerLayout.setScrimColor(Color.TRANSPARENT)

        activityMainBinding.navScroll.setOnClickListener(this)
        activityMainBinding.navSlide.setOnClickListener(this)
        activityMainBinding.navDoorIn.setOnClickListener(this)
        activityMainBinding.navDoorOut.setOnClickListener(this)

        setSupportActionBar(activityMainBinding.includeToolbar.toolbar)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_action_name)

        // Implement the drawer listener
        activityMainBinding.drawerLayout.setMIDrawerListener(object : MIDrawerView.MIDrawerEvents {
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
        if (activityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.nav_scroll -> {
                avoidDoubleClicks(activityMainBinding.navScroll)
                slideType = MI_TYPE_SLIDE_WITH_CONTENT
                updateSliderTypeEvents()
            }
            R.id.nav_slide -> {
                avoidDoubleClicks(activityMainBinding.navSlide)
                slideType = MI_TYPE_SLIDE
                updateSliderTypeEvents()
            }
            R.id.nav_doorIn -> {
                avoidDoubleClicks(activityMainBinding.navDoorIn)
                slideType = MI_TYPE_DOOR_IN
                updateSliderTypeEvents()
            }
            R.id.nav_doorOut -> {
                avoidDoubleClicks(activityMainBinding.navDoorIn)
                slideType = MI_TYPE_DOOR_OUT
                updateSliderTypeEvents()
            }
        }
    }

    private fun updateSliderTypeEvents() {
        if (handler == null) {
            handler = Handler()
            activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START)
            handler?.postDelayed(runnable, 500)
        }
    }

    var handler: Handler? = null
    var runnable: Runnable = Runnable {
        when (slideType) {
            MI_TYPE_SLIDE_WITH_CONTENT -> {
                activityMainBinding.includeToolbar.toolbar.title = this@MIDrawerActivity.resources.getString(R.string.scroll)
            }
            MI_TYPE_SLIDE -> {
                activityMainBinding.includeToolbar.toolbar.title = this@MIDrawerActivity.resources.getString(R.string.slide)
            }
            MI_TYPE_DOOR_IN -> {
                activityMainBinding.includeToolbar.toolbar.title = this@MIDrawerActivity.resources.getString(R.string.doorIn)
            }
            MI_TYPE_DOOR_OUT -> {
                activityMainBinding.includeToolbar.toolbar.title = this@MIDrawerActivity.resources.getString(R.string.doorOut)
            }
        }
        activityMainBinding.drawerLayout.setSliderType(slideType)
        handler = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activityMainBinding.drawerLayout.openDrawer(GravityCompat.START)
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
