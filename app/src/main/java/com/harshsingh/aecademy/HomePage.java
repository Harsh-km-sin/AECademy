package com.harshsingh.aecademy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    WebView webView1, webView2;
    TextView subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        subjects = findViewById(R.id.sub);
        webView1 = findViewById(R.id.homeWebView1);
        webView2 = findViewById(R.id.homeWebView2);

        subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, ContentPage.class);
                startActivity(intent);
            }
        });

        String video1 = "<html><body>" +
                "<iframe width=\"350\" height=\"200\" src=\"https://www.youtube.com/embed/uAQs-YXnY-U?si=PXj3hVfZtF3WFiSi\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>" +
                "</body></html>";

        String video2 = "<html><body>" +
                "<iframe width=\"350\" height=\"200\" src=\"https://www.youtube.com/embed/1_MWCHpFsso?si=fhXXCxCmCHmJV_oR\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>" +
                "</body></html>";

        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebChromeClient(new WebChromeClient());
        webView1.loadData(video1, "text/html", "utf-8");

        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setWebChromeClient(new WebChromeClient());
        webView2.loadData(video2, "text/html", "utf-8");
    }
}
