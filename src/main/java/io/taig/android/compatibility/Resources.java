package io.taig.android.compatibility;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.M;

public class Resources {
    @ColorInt
    @SuppressWarnings( "deprecation" )
    public static int getColor( android.content.res.Resources resources, @ColorRes int resource ) {
        if( SDK_INT >= M ) {
            return resources.getColor( resource, null );
        } else {
            return resources.getColor( resource );
        }
    }

    @SuppressWarnings( "deprecation" )
    public static Drawable getDrawable( android.content.res.Resources resources, @DrawableRes int resource ) {
        if( SDK_INT >= M ) {
            return resources.getDrawable( resource, null );
        } else {
            return resources.getDrawable( resource );
        }
    }
}