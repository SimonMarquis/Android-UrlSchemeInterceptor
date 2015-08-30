package fr.smarquis.usi.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.Date;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void trigger(View view) {
        startActivity(Intent.createChooser(createIntent(), null));
    }

    @NonNull
    private Intent createIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW, buildScheme());
        intent.setPackage(getPackageName());
        intent.putExtra("timestamp", System.currentTimeMillis());
        intent.putExtra("date", new Date().toString());
        return intent;
    }

    private Uri buildScheme() {
        return Uri.parse(getString(R.string.usi_scheme) + "://example.com:8042/over/there/index.html?query=something#refresh");
    }
}
