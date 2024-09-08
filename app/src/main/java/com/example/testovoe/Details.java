package com.example.testovoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

public class Details extends AppCompatActivity {

    AppCompatButton Link;
    ImageView DetailImage;
    TextView DetailName;
    TextView DetailRating;
    TextView DetailDiscription;
    TextView DetailGenre;
    TextView DetailYear;
    ImageView DetailFrame1;
    ImageView DetailFrame2;
    ImageView DetailFrame3;
    ImageView DetailFrame4;
    ImageView DetailFrame5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        DetailName = findViewById(R.id.DetailName);
        DetailImage = findViewById(R.id.imageView);
        DetailGenre = findViewById(R.id.DetailGenre);
        DetailRating = findViewById(R.id.DetailRating);
        DetailDiscription = findViewById(R.id.Discription);
        DetailYear = findViewById(R.id.DetailYear);
        DetailFrame1 = findViewById(R.id.Frame1);
        DetailFrame2 = findViewById(R.id.Frame2);
        DetailFrame3 = findViewById(R.id.Frame3);
        DetailFrame4 = findViewById(R.id.Frame4);
        DetailFrame5 = findViewById(R.id.Frame5);
        Link = findViewById(R.id.Link);

        String url = "";
        /*
        * После запроса получить данные из поля API  "webUrl", присвоить переменной url на фильм
        * Для примера вставлена уже готовая ссылка на фильм, но после подключения API строку:
        * intent.setData(Uri.parse("https://www.kinopoisk.ru/film/77044/"));
        * Нужно заменить на:
        * intent.setData(Uri.parse(url));
        * */




        Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.kinopoisk.ru/film/77044/"));
                startActivity(intent);
            }
        });





        String id = getIntent().getStringExtra("Id");

        //После подключения API отправить запрос по полученному id и получить информацию о фильме а также кадры:
        /*
        DetailName.setText(filmlist.get(position).getString("nameOriginal"));
        DetailYear.setText(filmlist.get(position).getString("year"+", "+ "countries.country"));
        DetailGenre.setText(filmlist.get(position).getString("genres.genre"));
        DetailRating.setText(filmlist.get(position).getString("ratingKinopoisk"));
        DetailImage.setImageResource(filmlist.get(position).getInt("posterUrlPreview"));
        DetailFrame1.setImageResource(filmlist.get(position).getInt("posterUrlPreview"));
        DetailFrame2.setImageResource(filmlist.get(position).getInt("posterUrlPreview"));
        DetailFrame3.setImageResource(filmlist.get(position).getInt("posterUrlPreview"));
        DetailFrame4.setImageResource(filmlist.get(position).getInt("posterUrlPreview"));
        DetailFrame5.setImageResource(filmlist.get(position).getInt("posterUrlPreview"));
        */




    }









}