package com.example.android.campsitemanager;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    static final String API_KEY = "OoHPawan52M008dogcoBEBDPL0N2IBxC";
    static final String API_URL = "http://192.168.160.223:8080/goCamping/rest/campsite";
    EditText managerText;
    TextView responseView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseView = (TextView) findViewById(R.id.responseView);
        managerText = (EditText) findViewById(R.id.emailText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            String manager = "/" + managerText.getText().toString();
            if (manager.length() == 1) {
                manager = "";
            }
            // Do some validation here

            try {
                URL url = new URL(API_URL + manager);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            Log.i("MINE1", response);


            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = null;
                Document doc;
                dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(new InputSource(new StringReader(response)));
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("campsite");
                StringBuilder stringBuilder = new StringBuilder();

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        stringBuilder.append("Adult Price : " + eElement.getElementsByTagName("adultPrice").item(0).getTextContent()).append("\n");
                        stringBuilder.append("Child Price : " + eElement.getElementsByTagName("childPrice").item(0).getTextContent()).append("\n");
                        stringBuilder.append("Baby Price : " + eElement.getElementsByTagName("babyPrice").item(0).getTextContent()).append("\n");
                        stringBuilder.append("Card Discount : " + eElement.getElementsByTagName("campingCardDiscount").item(0).getTextContent()).append("\n");
                        stringBuilder.append("Contact : " + eElement.getElementsByTagName("contact").item(0).getTextContent()).append("\n");
                        stringBuilder.append("Description : " + eElement.getElementsByTagName("description").item(0).getTextContent()).append("\n");
                        stringBuilder.append("Location : " + eElement.getElementsByTagName("location").item(0).getTextContent()).append("\n");
                        stringBuilder.append("Title : " + eElement.getElementsByTagName("title").item(0).getTextContent()).append("\n");

                        Log.i("MINE", stringBuilder.toString());

                    }
                }
                stringBuilder.toString();
                responseView.setText(stringBuilder);

            } catch (ParserConfigurationException e) {
                Log.i("MINE", "Catch1");
                e.printStackTrace();
            } catch (SAXException e) {
                Log.i("MINE", "Catch2");
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("MINE", "Catch3");
                e.printStackTrace();
            }


        }
    }

}
