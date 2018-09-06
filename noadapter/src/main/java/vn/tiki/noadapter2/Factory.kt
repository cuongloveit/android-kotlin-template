package vn.tiki.noadapter2

/**
 * Created by Giang Nguyen on 1/13/17.
 */
interface Factory {

  fun typeFactory(): TypeFactory

  fun viewHolderFactory(): ViewHolderFactory
}
