package io.taig.android.content

/**
 * A Fragment may be a Creditor, forcing the hosting Activity to implement its Contract
 */
trait	Creditor[+C]
extends	internal.Creditor[C]