package io.taig.android.monix

class EventSink[T] {
    private val listeners = collection.mutable.ListBuffer.empty[T ⇒ Unit]

    def register( f: T ⇒ Unit ): Unit = listeners += f

    def unregister( f: T ⇒ Unit ): Unit = listeners -= f

    def notify( event: T ): Unit = listeners.foreach( _.apply( event ) )
}