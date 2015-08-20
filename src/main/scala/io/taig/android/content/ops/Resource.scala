package io.taig.android.content.ops

import android.content.ContentResolver
import android.graphics.drawable.Drawable
import android.net.Uri
import com.wnafee.vector.compat.ResourcesCompat
import io.taig.android.content.Contextual
import io.taig.android.content.ops.Resource.ResourceResolver
import io.taig.android.graphic.Color

trait Resource
        extends Contextual {
    def resource: Int

    def as[T: ResourceResolver]: T = implicitly[ResourceResolver[T]].apply( resource )
}

object Resource {
    trait ResourceResolver[T] {
        def apply( resource: Int )( implicit context: android.content.Context ): T
    }

    implicit val `ResourceResolver[Boolean]` = new ResourceResolver[Boolean] {
        override def apply( resource: Int )( implicit context: android.content.Context ) = {
            context.getResources.getBoolean( resource )
        }
    }

    implicit val `ResourceResolver[Color]` = new ResourceResolver[Color] {
        override def apply( resource: Int )( implicit context: android.content.Context ) = {
            context.getResources.getColor( resource, null )
        }
    }

    implicit val `ResourceResolver[Drawable]` = new ResourceResolver[Drawable] {
        override def apply( resource: Int )( implicit context: android.content.Context ) = {
            ResourcesCompat.getDrawable( context, resource )
        }
    }

    implicit val `ResourceResolver[Int]` = new ResourceResolver[Int] {
        override def apply( resource: Int )( implicit context: android.content.Context ) = {
            context.getResources.getInteger( resource )
        }
    }

    implicit val `ResourceResolver[Array[Int]]` = new ResourceResolver[Array[Int]] {
        override def apply( resource: Int )( implicit context: android.content.Context ) = {
            context.getResources.getIntArray( resource )
        }
    }

    implicit val `ResourceResolver[String]` = new ResourceResolver[String] {
        override def apply( resource: Int )( implicit context: android.content.Context ) = {
            context.getResources.getString( resource )
        }
    }

    implicit val `ResourceResolver[Array[String]]` = new ResourceResolver[Array[String]] {
        override def apply( resource: Int )( implicit context: android.content.Context ) = {
            context.getResources.getStringArray( resource )
        }
    }

    implicit val `ResourceResolver[Uri]` = new ResourceResolver[Uri] {
        override def apply( resource: Int )( implicit context: android.content.Context ) = {
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    context.getResources.getResourcePackageName( resource ) + "/" +
                    context.getResources.getResourceTypeName( resource ) + "/" +
                    context.getResources.getResourceEntryName( resource ) + "/"
            )
        }
    }
}