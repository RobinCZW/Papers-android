package com.example.robin.papers.demo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.robin.papers.R;

public class SubmitSuccessActivity extends Activity {
    private ImageView submit_success_back_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_success);
        submit_success_back_iv = (ImageView) findViewById(R.id.submit_success_back_iv);
        submit_success_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
