package vn.tiki.noadapter2

/**
 * Default implementation of [DiffCallback]. This used [Object.equals] to check.
 */
internal class DefaultDiffCallback : DiffCallback {
  override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
    return oldItem == newItem
  }

  override fun equals(o: Any?): Boolean {
    return this === o || !(o == null || javaClass != o.javaClass)
  }

  override fun hashCode(): Int {
    return 0
  }
}
