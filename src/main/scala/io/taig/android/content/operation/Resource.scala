package io.taig.android.content.operation

import android.content.ContentResolver
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.annotation._
import com.wnafee.vector.compat.ResourcesCompat
import io.taig.android.compatibility
import io.taig.android.extension.graphic._
import io.taig.android.content.operation.Resource.ResourceResolver
import io.taig.android.content.{ Contextual, Quantity }
import io.taig.android.graphic.Color

abstract class Resource[A]( resource: A ) extends Contextual {
    def as[B]( implicit resolver: ResourceResolver[A, B] ): B = resolver.resolve( resource, Seq.empty )

    def as[B]( arguments: Any* )( implicit resolver: ResourceResolver[A, B] ): B = {
        resolver.resolve( resource, arguments )
    }
}

object Resource {
    trait ResourceResolver[A, B] {
        def resolve( resource: A, arguments: Seq[Any] )( implicit context: android.content.Context ): B
    }

    implicit val `ResourceResolver[Int, Boolean]` = new ResourceResolver[Int, Boolean] {
        override def resolve( @BoolRes resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            context.getResources.getBoolean( resource )
        }
    }

    implicit val `ResourceResolver[Int, Color]` = new ResourceResolver[Int, Color] {
        override def resolve( @ColorRes resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            compatibility.Resources.getColor( context.getResources, resource )
        }
    }

    implicit val `ResourceResolver[Int, Drawable]` = new ResourceResolver[Int, Drawable] {
        override def resolve( @DrawableRes resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            ResourcesCompat.getDrawable( context, resource )
        }
    }

    implicit val `ResourceResolver[Int, Int]` = new ResourceResolver[Int, Int] {
        override def resolve( @AnyRes resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            context.getResources.getResourceTypeName( resource ) match {
                case "integer" ⇒ context.getResources.getInteger( resource )
                case "dimen"   ⇒ context.getResources.getDimensionPixelSize( resource )
                case name      ⇒ sys.error( s"Invalid resource type $name given to resolve an Int" )
            }
        }
    }

    implicit val `ResourceResolver[Int, Array[Int]]` = new ResourceResolver[Int, Array[Int]] {
        override def resolve( resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            context.getResources.getIntArray( resource )
        }
    }

    implicit val `ResourceResolver[Int, String]` = new ResourceResolver[Int, String] {
        override def resolve( @StringRes resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            context.getResources.getString( resource, arguments.map( _.asInstanceOf[AnyRef] ): _* )
        }
    }

    implicit val `ResourceResolver[Int, Float]` = new ResourceResolver[Int, Float] {
        override def resolve( @DimenRes resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            context.getResources.getDimension( resource )
        }
    }

    implicit val `ResourceResolver[Int, Array[String]]` = new ResourceResolver[Int, Array[String]] {
        override def resolve( resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            context.getResources.getStringArray( resource )
        }
    }

    implicit val `ResourceResolver[Int, Uri]` = new ResourceResolver[Int, Uri] {
        override def resolve( resource: Int, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    context.getResources.getResourcePackageName( resource ) + "/" +
                    context.getResources.getResourceTypeName( resource ) + "/" +
                    context.getResources.getResourceEntryName( resource ) + "/"
            )
        }
    }

    implicit val `ResourceResolver[Quantity, String]` = new ResourceResolver[Quantity, String] {
        override def resolve( quantity: Quantity, arguments: Seq[Any] )( implicit context: android.content.Context ) = {
            context.getResources.getQuantityString(
                quantity.message,
                quantity.count,
                arguments.map( _.asInstanceOf[AnyRef] ): _*
            )
        }
    }
}