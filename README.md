# Navigation Drawer Customisations [![](https://jitpack.io/v/mindinventory/minavdrawer.svg)](https://jitpack.io/#mindinventory/minavdrawer)

Easy to use customisations of standard NavigationDrawer

| DoorIn                      | Slide                      | Scroll                     | DoorOut                    |
| --------------------------- | -------------------------- | -------------------------- | -------------------------- |
| ![image](/media/doorIn.gif) | ![image](/media/slide.gif) | ![image](/media/scroll.gif)| ![image](/media/doorOut.gif)|

# Usage

* Dependencies

    Step 1. Add the JitPack repository to your build file:
    
    Add it in your root build.gradle at the end of repositories:

    ```groovy
	    allprojects {
		    repositories {
			    ...
			    maven { url 'https://jitpack.io' }
		    }
	    }
    ```


    Step 2. Add the dependency
    ```groovy
	    dependencies {
		    implementation 'com.github.mindinventory:minavdrawer:<X.X.X>'
	    }
    ```
    
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
    app:sliderType="doorIn"
    tools:openDrawer="start">
```

* Use attribute for the Drawer sliding style from xml.

```groovy
app:sliderType="doorIn"

// Here, sliderType types are following.

// scroll (For sliding view with content)
// slide (Open a drawer with content sliding with scale and rotation both.)
// doorIn (Open a drawer inside with content sliding with scale only)
// doorOut (Open a drawer outside with content sliding with scale only)
```

 * Use attribute for the Drawer sliding style from dynamically in kotlin or Java class.

```groovy
// You can use is dynamically with following content.
drawer_layout.setSliderType(slideType)


// Here, slideType types are following.

// (For sliding view with content)
// MIDrawerView.MI_TYPE_SLIDE_WITH_CONTENT (For scroll)

// (Open a drawer with content sliding with scale and rotation both.)
// MIDrawerView.MI_TYPE_SLIDE (For slide)

// (Open a drawer inside with content sliding with scale only)
// MIDrawerView.MI_TYPE_DOOR_IN (For doorIn)

// (Open a drawer outside with content sliding with scale only)
// MIDrawerView.MI_TYPE_DOOR_OUT (For doorOut)
```

# LICENSE!

MIDrawerView is [MIT-licensed](https://github.com/mindinventory1/minavdrawer/blob/master/LICENSE).

# Let us know!
We’d be really happy if you send us links to your projects where you use our component. Just send an email to sales@mindinventory.com And do let us know if you have any questions or suggestion regarding our work.
