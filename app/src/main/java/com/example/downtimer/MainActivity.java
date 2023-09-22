package com.example.downtimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //set end time in seconds
    private int duration = 120;
    private boolean timerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView hour = findViewById(R.id.hour);
        final TextView min = findViewById(R.id.min);
        final TextView sec = findViewById(R.id.sec);
        final AppCompatButton start_btn = findViewById(R.id.start_btn);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!timerRunning) {
                    timerRunning = true;
                    new CountDownTimer(duration * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                                    final String[] hourMinSec = time.split(":");

                                    hour.setText(hourMinSec[0]);
                                    min.setText(hourMinSec[1]);
                                    sec.setText(hourMinSec[2]);
                                }
                            });
                        }

                        @Override
                        public void onFinish() {

                            duration = 120;

                            timerRunning = false;
                        }
                    }.start();
                } else {
                    Toast.makeText(MainActivity.this, "Timer is already running", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}