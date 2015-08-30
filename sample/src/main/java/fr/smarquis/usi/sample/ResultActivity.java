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
                        truss.append(Arrays.toString((Object[]) obj));
                    } else {
                        truss.append(String.valueOf(obj));
                    }
                }
            }
        }
        return truss.build();
    }

}
