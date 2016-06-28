package io.taig.android.extension

import android.content.Context
import io.taig.android.content.operation

package object content {
    implicit class ToolbeltContext( context: Context ) extends operation.Context( context )

    implicit class ToolbeltResource[T]( resource: T )( implicit val context: Context )
        extends operation.Resource[T]( resource )

    implicit class ToolbeltService( context: Context ) extends operation.Service( context )

    implicit class ToolbeltUnit[T: Numeric]( unit: T )( implicit val context: Context ) extends operation.Unit[T]( unit )
}