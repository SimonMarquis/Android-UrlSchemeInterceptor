package fr.smarquis.urlscheme;

import android.graphics.Typeface;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import java.util.Arrays;
import java.util.Set;

public class Printer {

    public static final String EMPTY = "\u2205";

    public static void appendWithClass(Truss truss, String key, Object value) {
        appendKey(truss, key);
        appendValueWithClass(truss, value);
    }

    public static void append(Truss truss, String key, Object value) {
        appendKey(truss, key);
        appendValue(truss, value);
    }

    public static void appendSecondary(Truss truss, String key, String value) {
        appendSecondaryKey(truss, key);
        appendSecondaryValue(truss, value);
    }

    public static void appendKey(Truss truss, String key) {
        truss.pushSpan(new StyleSpan(Typeface.BOLD))
                .append(key).append("\n")
                .popSpan();
    }

    private static void appendValue(Truss truss, Object value) {
        if (value == null) {
            truss.append(EMPTY);
        } else if (value instanceof Set) {
            Set set = (Set) value;
            Object[] array = set.toArray();
            truss.append(Arrays.toString(array));
        } else if (value instanceof Object[]) {
            truss.append(Arrays.toString((Object[]) value));
        } else {
            truss.append(value.toString());
        }
        truss.append("\n\n");
    }

    private static void appendSecondaryKey(Truss truss, String key) {
        truss.pushSpan(new StyleSpan(Typeface.BOLD))
                .append("      ").pushSpan(new RelativeSizeSpan(0.8f)).append(key).append("\n").popSpan()
                .popSpan();
    }

    private static void appendSecondaryValue(Truss truss, String value) {
        truss.append("      ").pushSpan(new RelativeSizeSpan(0.8f)).append(value).popSpan().append("\n\n");
    }

    private static void appendValueWithClass(Truss truss, Object value) {
        if (value == null) {
            truss.append(EMPTY);
        } else {
            Class<?> clazz = value.getClass();
            if (clazz.isArray()) {
                truss.append(Arrays.toString((Object[]) value));
            } else {
                truss.append(value.toString());
            }
            truss.pushSpan(new StyleSpan(Typeface.ITALIC))
                    .pushSpan(new RelativeSizeSpan(0.6f))
                    .append("   as ").append(clazz.getSimpleName())
                    .popSpan()
                    .popSpan();


        }
        truss.append("\n\n");
    }

}
