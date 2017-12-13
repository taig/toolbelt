package io.taig.android.mosby.delegate;

import android.app.Fragment;
import android.util.Log;

import java.lang.reflect.Method;

public class BackstackAccessor {
    private static Method method;

    static {
        try {
            method = Fragment.class.getDeclaredMethod( "isInBackStack" );
            method.setAccessible( true );
        }
        catch( NoSuchMethodException exception ) {
            Log.e( "BackstackAccessor", "Reflection access failed", exception  );
        }
    }

    private BackstackAccessor() { }

    public static boolean isFragmentOnBackStack( Fragment fragment ) {
        try {
            return (boolean) method.invoke( fragment );
        }
        catch( ReflectiveOperationException exception ) {
            Log.e( "BackstackAccessor", "Reflection access failed", exception );
            return false;
        }
    }
}

