package io.taig.android.content.operation

import android.content.{ ContentResolver, Context ⇒ AContext }
import android.graphics.drawable._
import android.graphics.{ Bitmap, Canvas }
import android.net.Uri
import android.support.annotation._
import io.taig.android.compatibility
import io.taig.android.content.Quantity
import io.taig.android.content.operation.Resource.ResourceResolver
import io.taig.android.context.Contextual
import io.taig.android.extension.content._
import io.taig.android.extension.graphic._
import io.taig.android.graphic.Color

abstract class Resource[A]( resource: A ) extends Contextual {
    def as[B]( implicit resolver: ResourceResolver[A, B] ): B = resolver.resolve( resource, Seq.empty )

    def as[B]( arguments: Any* )( implicit resolver: ResourceResolver[A, B] ): B = {
        resolver.resolve( resource, arguments )
    }
}

object Resource {
    trait ResourceResolver[A, B] {
        def resolve( resource: A, arguments: Seq[Any] )( implicit c: AContext ): B
    }

    implicit val `ResourceResolver[Int, Boolean]` = new ResourceResolver[Int, Boolean] {
        override def resolve( @BoolRes resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getBoolean( resource )
        }
    }

    implicit val `ResourceResolver[Int, CharSequence]` = new ResourceResolver[Int, CharSequence] {
        override def resolve( @StringRes resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getText( resource )
        }
    }

    implicit val `ResourceResolver[Int, Color]` = new ResourceResolver[Int, Color] {
        override def resolve( @ColorRes resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            compatibility.Resources.getColor( c.getResources, resource )
        }
    }

    implicit val `ResourceResolver[Int, Drawable]` = new ResourceResolver[Int, Drawable] {
        override def resolve( @DrawableRes resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            compatibility.Resources.getDrawable( c.getResources, resource )
        }
    }

    implicit val `ResourceResolver[Int, Int]` = new ResourceResolver[Int, Int] {
        override def resolve( @AnyRes resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getResourceTypeName( resource ) match {
                case "integer" ⇒ c.getResources.getInteger( resource )
                case "dimen"   ⇒ c.getResources.getDimensionPixelSize( resource )
                case name      ⇒ sys.error( s"Invalid resource type $name given to resolve an Int" )
            }
        }
    }

    implicit val `ResourceResolver[Int, Array[Int]]` = new ResourceResolver[Int, Array[Int]] {
        override def resolve( resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getIntArray( resource )
        }
    }

    implicit val `ResourceResolver[Int, String]` = new ResourceResolver[Int, String] {
        override def resolve( @StringRes resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getString( resource, arguments.map( _.asInstanceOf[AnyRef] ): _* )
        }
    }

    implicit val `ResourceResolver[Int, Float]` = new ResourceResolver[Int, Float] {
        override def resolve( @DimenRes resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getDimension( resource )
        }
    }

    implicit val `ResourceResolver[Int, Array[CharSequence]]` = new ResourceResolver[Int, Array[CharSequence]] {
        override def resolve( resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getTextArray( resource )
        }
    }

    implicit val `ResourceResolver[Int, Array[String]]` = new ResourceResolver[Int, Array[String]] {
        override def resolve( resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getStringArray( resource )
        }
    }

    implicit val `ResourceResolver[Int, Uri]` = new ResourceResolver[Int, Uri] {
        override def resolve( resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    c.getResources.getResourcePackageName( resource ) + "/" +
                    c.getResources.getResourceTypeName( resource ) + "/" +
                    c.getResources.getResourceEntryName( resource ) + "/"
            )
        }
    }

    implicit val `ResourceResolver[Quantity, String]` = new ResourceResolver[Quantity, String] {
        override def resolve( quantity: Quantity, arguments: Seq[Any] )( implicit c: AContext ) = {
            c.getResources.getQuantityString(
                quantity.message,
                quantity.count,
                arguments.map( _.asInstanceOf[AnyRef] ): _*
            )
        }
    }

    implicit val `ResourceResolver[Int, Bitmap]` = new ResourceResolver[Int, Bitmap] {
        override def resolve( @DrawableRes resource: Int, arguments: Seq[Any] )( implicit c: AContext ) = {
            resource.as[Drawable] match {
                case bitmap: BitmapDrawable ⇒ bitmap.getBitmap
                case drawable ⇒
                    drawable.setBounds( 0, 0, drawable.getIntrinsicWidth, drawable.getIntrinsicHeight )
                    val bitmap = Bitmap.createBitmap(
                        drawable.getIntrinsicWidth,
                        drawable.getIntrinsicHeight,
                        Bitmap.Config.ARGB_8888
                    )

                    val canvas = new Canvas( bitmap )
                    drawable.draw( canvas )
                    bitmap
            }
        }
    }
}