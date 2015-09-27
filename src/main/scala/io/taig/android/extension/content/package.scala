package io.taig.android.extension

import android.content.Context
import io.taig.android.content.operation

package object content {
    implicit class RichContext( context: Context ) extends operation.Context( context )

    implicit class RichIntent( val intent: android.content.Intent )( implicit val context: Context )
        extends operation.Intent( intent )

    implicit class RichResource[T]( resource: T )( implicit val context: Context )
        extends operation.Resource[T]( resource )

    implicit class RichService( context: Context ) extends operation.Service( context )

    implicit class RichUnit[T: Numeric]( unit: T )( implicit val context: Context ) extends operation.Unit[T]( unit )
}