package io.taig.android.compatibility;

import static android.text.Html.FROM_HTML_MODE_LEGACY;
import android.text.Spanned;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.N;

public class Html {
    @SuppressWarnings( "deprecation" )
    public static Spanned fromHtml( String source ) {
        if( SDK_INT >= N ) {
            return android.text.Html.fromHtml( source, FROM_HTML_MODE_LEGACY );
        } else {
            return android.text.Html.fromHtml( source );
        }
    }
}