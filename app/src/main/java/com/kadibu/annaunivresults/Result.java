package com.kadibu.annaunivresults;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;


import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.kadibu.annaunivresults.R.id.Webview;

public class Result extends AppCompatActivity {

    String no;
    String url;
    WebView webview;
    private ProgressDialog progressBar;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        no = i.getStringExtra("num");

        webview = (WebView) findViewById(Webview);
        url = "http://aucoe.annauniv.edu/cgi-bin/result/cgrade.pl?regno=" + no;



        progressBar = ProgressDialog.show(Result.this, "Connecting Server", "Loading...");

        webview.setWebViewClient(new WebViewClient() {


            public static final String TAG ="Main" ;

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);


                String summary = "<html><body style='background: #9b59b6;'><p style='color: white;'>Unable to load information. May be anna university server might be busy .Please check if your network connection is working properly or try again later.</p></body></html>";
                view.loadData(summary, "text/html", null);



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Result.this);

                alertDialogBuilder.setTitle("Error");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setMessage("Please Check the Internet Connection").setPositiveButton("Reload",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                webview.loadUrl(url);
                                dialog.cancel();

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();




            }


        });

        webview.loadUrl(url);

    }}

