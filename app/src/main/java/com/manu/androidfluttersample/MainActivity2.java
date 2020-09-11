package com.manu.androidfluttersample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.flutter.plugin.common.MethodChannel;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        channel.invokeMethod("getFlutterName", null, new MethodChannel.Result() {
//            @Override
//            public void success(Object o) {
//            }
//            @Override
//            public void error(String s, String s1, Object o) {
//            }
//            @Override
//            public void notImplemented() {
//            }
//        });

        TextView textView = new TextView(this);
        textView.setOnClickListener(v -> {
            textView.setText("");
            textView.setText("");
            textView.setText("");
        });

    }
}