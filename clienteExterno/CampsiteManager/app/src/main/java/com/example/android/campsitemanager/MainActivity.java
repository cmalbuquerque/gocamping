package com.example.android.campsitemanager;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Element;


public class MainActivity extends AppCompatActivity {

    static final String API_URL = "http://192.168.160.223:8080/goCamping/rest/campsite";
    EditText managerText;

    RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        managerText = (EditText) findViewById(R.id.emailText);
        progressBar = (RelativeLayout) findViewById(R.id.progressBar);

        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                new RetrieveFeedTask().execute();
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
        public String ids = "";
        protected void onPreExecute() {
            ClearAll(ids);
            progressBar.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(Void... urls) {
            String manager = "/" + managerText.getText().toString();
            if (manager.length() == 1) {
                manager = "";
            }

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

        @SuppressLint("ResourceType")
        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);


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

                        //campsiteView.setVisibility(View.VISIBLE);


                        ids = addChildLayout(eElement, ids);


/**
 stringBuilder.append("Adult Price : " + eElement.getElementsByTagName("adultPrice").item(0).getTextContent()).append("\n");
 stringBuilder.append("Child Price : " + eElement.getElementsByTagName("childPrice").item(0).getTextContent()).append("\n");
 stringBuilder.append("Baby Price : " + eElement.getElementsByTagName("babyPrice").item(0).getTextContent()).append("\n");
 stringBuilder.append("Card Discount : " + eElement.getElementsByTagName("campingCardDiscount").item(0).getTextContent()).append("\n");
 stringBuilder.append("Contact : " + eElement.getElementsByTagName("contact").item(0).getTextContent()).append("\n");
 stringBuilder.append("Description : " + eElement.getElementsByTagName("description").item(0).getTextContent()).append("\n");
 stringBuilder.append("Location : " + eElement.getElementsByTagName("location").item(0).getTextContent()).append("\n");
 stringBuilder.append("Title : " + eElement.getElementsByTagName("title").item(0).getTextContent()).append("\n");
 **/
                    } else {
                        //campsiteView.setVisibility(View.GONE);
                    }
                }
                stringBuilder.toString();

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

        public String addChildLayout(Element eElement, String ids) {
            Log.i("MINE", "Add Child Layout");
            //Inflater service
            LayoutInflater layoutInfralte = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //parent layout xml refrence
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ListagemView);
            //Child layout xml refrence
            View view = layoutInfralte.inflate(R.layout.campsite, null);
            //add child to parent

            ids = view.getId() + "";
            Log.i("MINE", ids);


            TextView titleView = (TextView) view.findViewById(R.id.TitleView);
            TextView priceView = (TextView) view.findViewById(R.id.PriceView);
            TextView locationView = (TextView) view.findViewById(R.id.LocationView);
            TextView discountView = (TextView) view.findViewById(R.id.DiscountView);


            titleView.setText(eElement.getElementsByTagName("title").item(0).getTextContent());
            locationView.setText(eElement.getElementsByTagName("location").item(0).getTextContent());
            priceView.setText(eElement.getElementsByTagName("adultPrice").item(0).getTextContent() + "€");
            discountView.setText(eElement.getElementsByTagName("campingCardDiscount").item(0).getTextContent() + "%");

            linearLayout.addView(view);
            return ids;
        }

        public void ClearAll(String ids){
            ViewGroup viewGroup = findViewById(R.id.ListagemView);
            viewGroup.removeAllViews();
        }
    }

}
