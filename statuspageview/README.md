StatusPageView
===
 Show status (Loading, Success, Error) of activity, fragment or view 

Download
--------

```groovy
allprojects {
  repositories {
	   ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.cuongloveit.android-kotlin-template:statuspageview:-SNAPSHOT'
}
```

## Usage

~~~xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

  <android.support.v7.widget.RecyclerView
      android:id="@+id/recyclerView"
      android:layout_width="match_parent"
      android:layout_height="match_parent" />

  <com.givestech.statuspageview.StatusPageView
      android:id="@+id/statusPageView"
      android:layout_width="match_parent"
      android:layout_height="match_parent" />

</android.support.constraint.ConstraintLayout>
~~~

 ~~~kotlin
 override fun onGetRepositoriesSuccess(items: List<Repository>) {
     adapter.setItems(items)
     statusPageView.showContent()
   }
 
   override fun onError() {
     statusPageView.showMessage("Error")
   }
 
   override fun showLoading() {
     statusPageView.showLoading()
   }
 ~~~
 




