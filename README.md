# minavdrawer
Easy to use customisations of standard NavigationDrawer

            Door            |            Slide            |              Scroll
:--------------------------:|:---------------------------:|:------------------------------:
  ![image](/media/Door.png) |  ![image](/media/Slide.png) |   ![image](/media/Scroll.png)

  
# Usage

* MIDrawerView.kt is the DrawerView component, add it to your xml file for drawer view.

```groovy
<com.mindinventory.midrawer.MIDrawerView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/bg_gradient"
    android:fitsSystemWindows="true"
    app:sliderType="Door"
    tools:openDrawer="start">
```

* Use attribute for the Drawer sliding style from xml.

```groovy
app:sliderType="Door"

// Here, sliderType types are following.

// Scroll (For sliding view with content)
// Slide (Open a drawer with content sliding with scale and rotation both.)
// Door (Open a drawer with content sliding with scale only)
```

 * Use attribute for the Drawer sliding style from dynamically in kotlin or Java class.

```groovy
// You can use is dynamically with following content.
drawer_layout.setSliderType(slideType)


// Here, slideType types are following.

// (For sliding view with content)
// MIDrawerView.MI_TYPE_SLIDE_WITH_CONTENT (For Scroll)

// (Open a drawer with content sliding with scale and rotation both.)
// MIDrawerView.MI_TYPE_SLIDE (For Slide)

// (Open a drawer with content sliding with scale only)
// MIDrawerView.MI_TYPE_DOOR (For Door)
```

# LICENSE!

MIDrawerView is [MIT-licensed](http://192.168.1.240:8888/mi-codelib/MiNavigationDrawer/blob/master/LICENSE).

# Let us know!
We’d be really happy if you sent us links to your projects where you use our component. Just send an email to sales@mindinventory.com And do let us know if you have any questions or suggestion regarding our work.
