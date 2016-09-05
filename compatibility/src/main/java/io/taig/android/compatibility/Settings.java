package io.taig.android.compatibility;

import android.content.ContentResolver;
import android.content.Context;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.*;

public class Settings {
    @SuppressWarnings( "deprecation" )
    public static String AUTO_TIME() {
        if( SDK_INT <= JELLY_BEAN ) { 
            return android.provider.Settings.System.AUTO_TIME;
        } else {
            return android.provider.Settings.Global.AUTO_TIME;
        }
    }

    @SuppressWarnings( "deprecation" )
    public static String AUTO_TIME_ZONE() {
        if( SDK_INT <= JELLY_BEAN ) {
            return android.provider.Settings.System.AUTO_TIME_ZONE;
        } else {
            return android.provider.Settings.Global.AUTO_TIME_ZONE;
        }
    }

    public static int getInt( ContentResolver resolver, String name, int defaultValue ) {
        if( name.equals( AUTO_TIME() ) || name.equals( AUTO_TIME_ZONE() ) ) {
            if( SDK_INT <= JELLY_BEAN ) {
                return android.provider.Settings.System.getInt(
                    resolver,
                    name,
                    defaultValue
                );
            } else {
                return android.provider.Settings.Global.getInt(
                    resolver,
                    name,
                    defaultValue
                );
            }
        } else {
            throw new RuntimeException( "No compatibility implementation for " + name );
        }
    }

    @SuppressWarnings( "deprecation" )
    public static boolean canDrawOverlays( Context context ) {
        if( SDK_INT >= M ) {
            return android.provider.Settings.canDrawOverlays( context );
        } else {
            return true;
        }
    }
}