package com.mindinventory.midrawer

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.view.GravityCompat
import android.util.AttributeSet
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

/**
 *  Here is a view for the navigation Drawer.
 *  From which you can set the drawer style as requirements with only one param.
 */
class MIDrawerView : DrawerLayout {

    companion object {
        val MI_TYPE_SLIDE_WITH_CONTENT = 1 // For sliding view with content
        val MI_TYPE_DOOR_IN = 2 // Open a drawer inside with content sliding with scale and rotation both.
        val MI_TYPE_DOOR_OUT = 3 // Open a drawer outside with content sliding with scale and rotation both.
        val MI_TYPE_SLIDE = 4 // Open a drawer with content sliding with scale only
    }

    private val min = 0.7f
    private val max = 1.0f
    private val rotaionOffset = 20

    private var sliderType = 0

    internal var drawerListener: DrawerLayout.DrawerListener? = null

    constructor(context: Context) : this(context, null)
    var mMIDrawerEvents: MIDrawerEvents? = null

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.MIDrawerView)
        val sliderType = arr.getInt(R.styleable.MIDrawerView_sliderType, 0)
        setDrawerView(sliderType)
        arr.recycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setDrawerView(slideType: Int) {
        if (drawerListener != null) {
            // Remove callback after adding new listener with new type.
            removeDrawerListener(drawerListener!!)
        }
        drawerListener = object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // Getting content view.
                val contentView = getChildAt(0)

                // Getting navigation view.
                val navigationView = getChildAt(1)

                // Check app configuration for RTL or LTR
                val config = context.resources.configuration

                when (slideType) {
                    MI_TYPE_SLIDE_WITH_CONTENT -> if (navigationView != null) {
                        if (config.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                            contentView!!.translationX = -(navigationView.width * slideOffset)
                        } else {
                            contentView!!.translationX = navigationView.width * slideOffset
                        }
                    }
                    MI_TYPE_DOOR_IN -> if (contentView != null) {
                        val scaleFactor = max - (max - min) * slideOffset
                        val width = drawerView.width
                        var moveFactor = width * slideOffset
                        moveFactor *= scaleFactor
                        if (config.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                            // If app in RTL mode.
                            contentView.translationX = -moveFactor
                            contentView.rotationY = (-(moveFactor * -rotaionOffset) / width)
                        } else {
                            // If app in LTR mode.
                            contentView.translationX = moveFactor
                            contentView.rotationY = moveFactor * -rotaionOffset / width
                        }
                        contentView.scaleY = scaleFactor
                        contentView.scaleX = scaleFactor
                    }
                    MI_TYPE_DOOR_OUT -> if (contentView != null) {
                        val scaleFactor = max - (max - min) * slideOffset
                        val width = drawerView.width
                        var moveFactor = width * slideOffset
                        moveFactor *= scaleFactor
                        if (config.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                            // If app in RTL mode.
                            contentView.translationX = -moveFactor
                            contentView.rotationY = (-(moveFactor * -rotaionOffset) / width)
                        } else {
                            // If app in LTR mode.
                            contentView.translationX = moveFactor
                            contentView.rotationY = moveFactor * rotaionOffset / width
                        }
                        contentView.scaleY = scaleFactor
                        contentView.scaleX = scaleFactor
                    }
                    MI_TYPE_SLIDE -> if (contentView != null) {
                        val scaleFactor = max - (max - min) * slideOffset
                        val width = drawerView.width
                        var moveFactor = width * slideOffset
                        moveFactor *= scaleFactor
                        if (config.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                            // If app in RTL mode.
                            contentView.translationX = -moveFactor
                        } else {
                            // If app in LTR mode.
                            contentView.translationX = moveFactor
                        }
                        contentView.scaleY = scaleFactor
                        contentView.scaleX = scaleFactor
                    }
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                if (mMIDrawerEvents != null) {
                    mMIDrawerEvents?.onDrawerOpened(drawerView)
                }
            }

            override fun onDrawerClosed(drawerView: View) {
                if (mMIDrawerEvents != null) {
                    mMIDrawerEvents?.onDrawerClosed(drawerView)
                }
            }

            override fun onDrawerStateChanged(newState: Int) {

            }
        }
        if (drawerListener != null) {
            addDrawerListener(drawerListener!!)
        }
    }

    fun setSliderType(sliderType: Int) {
        this.sliderType = sliderType
        // If drawer was open while changing the drawer type, it will close.
        if (isDrawerOpen(GravityCompat.START)) {
            closeDrawers()
        }
        setDrawerView(sliderType)
    }

    /**
     * Set the listener for the drawer open and close events.
     */
    fun setMIDrawerListener(mDrawerEvents: MIDrawerEvents) {
        this.mMIDrawerEvents = mDrawerEvents
    }

    /**
     * Interface for the drawer events.
     */
    public interface MIDrawerEvents {
        fun onDrawerOpened(drawerView: View) {
        }
        fun onDrawerClosed(drawerView: View) {
        }
    }
}