package com.template.kotlintemplate

import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.*

class RxJavaTest {
  @Test
  fun checkOperator() {
    val arr = arrayListOf("a", "b", "c", "d")
    Observable.fromArray(arr)
//        .map { list ->
//          val list = ArrayList(arr)
//          list.add("e")
//          println("map: " + list.toString())
//          list
//        }
        .switchMap { list ->
          val sArr = ArrayList(list)
          sArr.add("e")
          println("switchMap: " + sArr.toString())
          Observable.just(sArr)
        }
        //.switchMap { }
        .subscribe {
          println("subscribe: " + it.toString())
        }
  }
}