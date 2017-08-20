package com.kadibu.annaunivresults;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b;
        final EditText regis;
        b=(Button)findViewById(R.id.button);
        regis=(EditText)findViewById(R.id.editText);


        Toast.makeText(this, "This server may be available only during Result time.All the best !!",
                Toast.LENGTH_LONG).show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOnline();

                if(isOnline()) {

                    String regisno = regis.getText().toString();
                    if (regisno.length() == 12) {
                        Intent i = new Intent(v.getContext(), Result.class);
                        i.putExtra("num", regisno);
                        startActivity(i);
                    }
                    else
                    {

                        Snackbar.make(findViewById(android.R.id.content), "Check Your Registration Number", Snackbar.LENGTH_LONG)

                                .show();
                    }
                }


            }
        });


    }
    public boolean isOnline() {

        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){

            Snackbar.make(findViewById(android.R.id.content), "No Internet Connection. Retry!", Snackbar.LENGTH_LONG)

                    .show();

            return false;
        }

        return true;
    }

}
