package com.example.testovoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Films extends AppCompatActivity {
    private static final String JSON_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/301";

    AppCompatButton Unlogin;
    ListView listVIew;
    EditText SearchYear;
    EditText Keyword;
    AppCompatButton Test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        listVIew = findViewById(R.id.ListView);
        loadJSONFromURL(JSON_URL);
        Test = findViewById(R.id.Test);
        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Films.this, Details.class);
                startActivity(intent);
            }
        });
        //Выход
        Unlogin = findViewById(R.id.Unlogin);
        Unlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Films.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadJSONFromURL(String url){
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        progressBar.setVisibility(ListView.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>(){
            @Override
            public void onResponse (String response){
                progressBar.setVisibility(View.VISIBLE);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("/api/v2.2/films/{id}");
                    ArrayList<JSONObject> listItems = getArrayListFromJSONArray(jsonArray);
                    ListAdapter adapter = new ListViewAdapter(getApplicationContext(),R.layout.element,R.id.ViewName,listItems);
                    listVIew.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){
        ArrayList<JSONObject> aList = new ArrayList<JSONObject>();
        try {
            if(jsonArray != null){
                for(int i = 0; i<jsonArray.length();i++){
                    aList.add(jsonArray.getJSONObject(i));
                }
            }
        }catch (JSONException js){
            js.printStackTrace();
        }
        return aList;
    }


    /***************************************/
    /* для сортировки по году выпуска фильма необходимо получить данные из поля:
    * SearchYear = findViewById(R.id.SearchYear);
    * String Year = SearchYear.getText().toString(); --- и отправить это в запрос поиска фильма по фльтру
    *
  'https://kinopoiskapiunofficial.tech/api/v2.2/films?order=RATING&type=ALL&ratingFrom=0&ratingTo=10&yearFrom=1994&yearTo=1994&page=1' \
  -H 'accept: application/json' \
  -H 'X-API-KEY: 9399cee8-8f91-4e21-9f77-a6d677794c75
    *
    *Полученные после запроса данные вывести используя функцию loadJSONFromURL и добавить в ListView c помощью функции getArrayListFromJSONArray*/
    /***************************************/

    /***************************************/
    /* для поиска по ключевому слову необходимо получить данные из поля:
    * Keyword = findViewById(R.id.Keyword);
    * String Keyword = Keyword.getText().toString(); --- и отправить это в запрос поиска фильма по ключевому слову
    *
  'https://kinopoiskapiunofficial.tech/api/v2.1/films/search-by-keyword?keyword=%D0%9C%D1%81%D1%82%D0%B8%D1%82%D0%B5%D0%BB%D0%B8&page=1' \
  -H 'accept: application/json' \
  -H 'X-API-KEY: 9399cee8-8f91-4e21-9f77-a6d677794c75'
    *
    *  Полученные после запроса данные вывести используя функцию loadJSONFromURL и добавить в ListView c помощью функции getArrayListFromJSONArray*/
    /***************************************/

}