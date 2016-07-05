package io.taig.android.resource

import android.content.{ ContentResolver, Context }
import android.graphics.{ Bitmap, Canvas }
import android.graphics.drawable.{ BitmapDrawable, Drawable }
import android.net.Uri
import io.taig.android.resource.syntax.resource._
import io.taig.android.compatibility
import io.taig.android.graphic.Color

trait ResourceResolver[A, B] {
    def resolve( context: Context, resource: A, arguments: Seq[Any] ): B
}

object ResourceResolver {
    def apply[A, B]( implicit rr: ResourceResolver[A, B] ): ResourceResolver[A, B] = rr

    def instance[A, B]( f: Context ⇒ ( A, Seq[Any] ) ⇒ B ): ResourceResolver[A, B] = {
        new ResourceResolver[A, B] {
            override def resolve( context: Context, resource: A, arguments: Seq[Any] ) = {
                f( context )( resource, arguments )
            }
        }
    }

    def instance[A, B]( f: ( Context, A, Seq[Any] ) ⇒ B ): ResourceResolver[A, B] = {
        new ResourceResolver[A, B] {
            override def resolve( context: Context, resource: A, arguments: Seq[Any] ) = {
                f( context, resource, arguments )
            }
        }
    }

    implicit val resourceResolver_Res_Boolean: ResourceResolver[Int, Boolean] = {
        instance { ( context, resource, _ ) ⇒
            context.getResources.getBoolean( resource )
        }
    }

    implicit val resourceResolver_Res_CharSequence: ResourceResolver[Int, CharSequence] = {
        instance { ( context, resource, _ ) ⇒
            context.getResources.getText( resource )
        }
    }

    implicit val resourceResolver_Res_Color: ResourceResolver[Int, Color] = {
        instance { ( context, resource, _ ) ⇒
            Color( compatibility.Resources.getColor( context.getResources, resource ) )
        }
    }

    implicit val resourceResolver_Res_Drawable: ResourceResolver[Int, Drawable] = {
        instance { ( context, resource, _ ) ⇒
            compatibility.Resources.getDrawable( context.getResources, resource )
        }
    }

    implicit val resourceResolver_Res_Int: ResourceResolver[Int, Int] = {
        instance { ( context, resource, _ ) ⇒
            context.getResources.getResourceTypeName( resource ) match {
                case "integer" ⇒ context.getResources.getInteger( resource )
                case "dimen"   ⇒ context.getResources.getDimensionPixelSize( resource )
                case name      ⇒ sys.error( s"Invalid resource type $name given to resolve an Int" )
            }
        }
    }

    implicit val resourceResolver_Res_ArrayInt: ResourceResolver[Int, Array[Int]] = {
        instance { ( context, resource, _ ) ⇒
            context.getResources.getIntArray( resource )
        }
    }

    implicit val resourceResolver_Res_String: ResourceResolver[Int, String] = {
        instance { ( context, resource, arguments ) ⇒
            context.getResources.getString( resource, arguments.map( _.asInstanceOf[AnyRef] ): _* )
        }
    }

    implicit val resourceResolver_Res_Float: ResourceResolver[Int, Float] = {
        instance { ( context, resource, _ ) ⇒
            context.getResources.getDimension( resource )
        }
    }

    implicit val resourceResolver_Res_ArrayCharSequence: ResourceResolver[Int, Array[CharSequence]] = {
        instance { ( context, resource, _ ) ⇒
            context.getResources.getTextArray( resource )
        }
    }

    implicit val resourceResolver_Res_ArrayString: ResourceResolver[Int, Array[String]] = {
        instance { ( context, resource, _ ) ⇒
            context.getResources.getStringArray( resource )
        }
    }

    implicit val resourceResolver_Res_Uri: ResourceResolver[Int, Uri] = {
        instance { ( context, resource, _ ) ⇒
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    context.getResources.getResourcePackageName( resource ) + "/" +
                    context.getResources.getResourceTypeName( resource ) + "/" +
                    context.getResources.getResourceEntryName( resource ) + "/"
            )
        }
    }

    implicit val resourceResolver_Quantity_String: ResourceResolver[Quantity, String] = {
        instance { ( context, quantity, arguments ) ⇒
            context.getResources.getQuantityString(
                quantity.message,
                quantity.count,
                arguments.map( _.asInstanceOf[AnyRef] ): _*
            )
        }
    }

    implicit val resourceResolver_Res_Bitmap: ResourceResolver[Int, Bitmap] = {
        instance { implicit context ⇒ ( resource, _ ) ⇒
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