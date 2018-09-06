package vn.tiki.noadapter2

/**
 * Created by Giang Nguyen on 12/17/16.
 */

internal class DefaultTypeFactory : TypeFactory {
  override fun typeOf(item: Any): Int {
    return 0
  }

  override fun equals(o: Any?): Boolean {
    return this === o || !(o == null || javaClass != o.javaClass)
  }

  override fun hashCode(): Int {
    return 0
  }
}
