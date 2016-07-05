package io.taig.android.compatibility;

import java.util.Locale;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class PhoneNumberUtils {
    @SuppressWarnings( "deprecation" )
    public static String formatNumber( String number ) {
        if( SDK_INT >= LOLLIPOP ) {
            return android.telephony.PhoneNumberUtils.formatNumber( number, Locale.getDefault().getCountry() );
        } else {
            return android.telephony.PhoneNumberUtils.formatNumber( number );
        }
    }
}