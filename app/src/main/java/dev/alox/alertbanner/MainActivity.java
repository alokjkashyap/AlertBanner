package dev.alox.alertbanner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertBanner alertBanner = AlertBanner.make((ViewGroup) findViewById(R.id.bannerView), Snackbar.LENGTH_LONG);
                alertBanner.setText("No internet detected");
                alertBanner.setBackgroundColor(getResources().getColor(R.color.red));
                alertBanner.setFont("fonts/nunito_bold.ttf");
                alertBanner.show();
            }
        });
    }
}
