package ponkberry.hoandemo.util;

import android.util.Log;

/**
 * Created by Ponk on 2/6/2017.
 */

public class UtilLog {
    private static boolean Debug = false;

    public static void setDebug (boolean b) {
        Debug = b;
    }

    public static void logD(String key, String value) {
        if(Debug) {
            Log.d(key, value);
        }
    }

    // Do not import any classes into the util folder except for the Log file as every Android
    // device has the Log class. This also means you can copy the same util folder to other
    // applications and it'd still work flawlessly

    // Log Commands
    // V = Verbose
    // D = Debug
    // E = Error
    // I = Info
    // W = Warn
    // A = Assert

    // Homework build all error logs
}
