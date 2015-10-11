package fr.smarquis.usi.sample;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.StyleSpan;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Set;

import fr.smarquis.usi.Truss;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ((TextView) findViewById(R.id.result)).setText(getDataString());
    }

    private CharSequence getDataString() {
        Truss truss = new Truss();
        truss.append(getIntent().getDataString());
        Bundle bundle = getIntent().getExtras();
        Set<String> extras = bundle.keySet();
        if (!extras.isEmpty()) {
            truss.append('\n');
            for (String extra : extras) {
                truss.append('\n').pushSpan(new StyleSpan(Typeface.BOLD)).append(extra).popSpan().append(": ");
                Object obj = bundle.get(extra);
                if (obj != null) {
                    Class<?> clazz = obj.getClass();
                    if (clazz.isArray()) {
                        Class<?> type = clazz.getComponentType();
                        if (type == long.class) {
                            truss.append(Arrays.toString((long[]) obj));
                        } else if (type == int.class) {
                            truss.append(Arrays.toString((int[]) obj));
                        } else if (type == char.class) {
                            truss.append(Arrays.toString((char[]) obj));
                        } else if (type == boolean.class) {
                            truss.append(Arrays.toString((boolean[]) obj));
                        } else if (type == byte.class) {
                            truss.append(Arrays.toString((byte[]) obj));
                        } else if (type == float.class) {
                            truss.append(Arrays.toString((float[]) obj));
                        } else if (type == short.class) {
                            truss.append(Arrays.toString((short[]) obj));
                        } else if (type == double.class) {
                            truss.append(Arrays.toString((double[]) obj));
                        } else {
                            truss.append(Arrays.toString((Object[]) obj));
                        }
                    } else {
                        truss.append(String.valueOf(obj));
                    }
                }
            }
        }
        return truss.build();
    }

}
