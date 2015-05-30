package io.taig.android.widget

/**
 * A view which has the purpose to display a specific model M
 * 
 * @tparam M Model type to host
 */
trait Settable[M]
{
	/**
	 * Apply the model's data to the view
	 */
	def set( model: M ): Unit
}