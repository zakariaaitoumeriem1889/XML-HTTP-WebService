package com.example.android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chargeDonnees();
    }

    private void chargeDonnees() {
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new TacheChargement().execute();
        } else {
            Toast.makeText(this, "Impossible de se connecter", Toast.LENGTH_LONG).show();
        }
    }

    private class TacheChargement extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPostExecute(String result) {
            TextView textView = (TextView) findViewById(R.id.texte);
            textView.setText(result);
        }

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet req = new HttpGet("www.isga.ma");
                HttpResponse response = client.execute(req);
                InputStream in;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String ligne = "";
                while ((ligne = bufferedReader.readLine()) != null) {
                    stringBuilder.append(ligne + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }
    }
}
