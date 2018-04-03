MVP
===
Simple implementation of MVP architecture.

 * Implement Presenter, provides some elegant methods to interact with view.
 * Provides base classes: `MvpActivity`, `MvpFragment` that will
 automatically attach/detach views base on appropriate lifecycle callback.

Base on:
* [java library](https://github.com/tikivn/android-template/tree/master/mvp)

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
  implementation 'com.github.cuongloveit.android-kotlin-template:mvp:-SNAPSHOT'
}
```

## Activity

 * Extend from MvpActivity.
 * Call connect(Presenter, View) on `onCreate()` callback.

~~~kotlin
class MainActivity : MvpActivity<LoginView, LoginPresenter>(), LoginView {
lateinit var presenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connect(presenter, this)
    }
}
~~~
## Fragment
  * Extends from MvpFragment
  * Call connect(presenter, view) before `onViewCreated()` callback.

~~~kotlin
class LoginFragment : MvpFragment<LoginView, LoginPresenter>(), LoginView {
    lateinit var presenter: LoginPresenter;
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view //your view
        connect(presenter,this)
        return view
    }
}
~~~
