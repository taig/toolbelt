package io.taig.android.compatibility;

import android.graphics.drawable.Drawable;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

public class View {
    @SuppressWarnings( "deprecation" )
    public static void setBackground( android.view.View view, Drawable background ) {
        if( SDK_INT >= JELLY_BEAN ) {
            view.setBackground( background );
        } else {
            view.setBackgroundDrawable( background );
        }
    }
}