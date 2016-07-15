package io.taig.android.compatibility;

import android.content.Context;

import java.util.Locale;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.N;

public class Configuration {
    @SuppressWarnings( "deprecation" )
    public static Locale locale( Context context ) {
        if( SDK_INT >= N ) {
            return context.getResources().getConfiguration().getLocales().get( 0 );
        } else {
            return context.getResources().getConfiguration().locale;
        }
        
    }
}