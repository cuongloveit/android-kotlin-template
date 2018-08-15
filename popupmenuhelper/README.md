PoupMenuHelper
===
  Show a menu popup menu easily, fast

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
  implementation 'com.github.cuongloveit.android-kotlin-template:popupmenuhelper:-SNAPSHOT'
}
```

## Usage

 ~~~kotlin
 PopupMenuHelper(view, {
       when (it.itemId) {
         //use string resource like a itemId
         R.string.rename -> rename()//first menu item
         R.string.remove -> remote()//second menu item
       }
     }, R.string.rename, R.string.remove)
     
 // R.string.rename, R.string.remove are  string resources
 ~~~
 




