package io.taig.android

import android.content.Context

package object content {
    implicit class RichContext( val context: Context ) extends operation.Context

    implicit class RichIntent( val intent: android.content.Intent )( implicit val context: Context )
        extends operation.Intent( intent )

    implicit class RichUnit[T: Numeric]( unit: T )( implicit val context: Context ) extends operation.Unit[T]( unit )

    implicit class RichService( val context: Context ) extends operation.Service

    implicit class RichResource[T]( resource: T )( implicit val context: Context )
        extends operation.Resource[T]( resource )
}