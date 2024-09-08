package com.example.testovoe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<JSONObject> {
    int listLayots;
    ArrayList<JSONObject> filmlist;
    Context context;
    public ListViewAdapter(Context context, int listLayots, int field, ArrayList<JSONObject> filmlist){
        super(context,listLayots, field, filmlist);
        this.context = context;
        this.listLayots = listLayots;
        this.filmlist = filmlist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(listLayots,null, false);
        TextView name = listViewItem.findViewById(R.id.ViewName);
        TextView genre = listViewItem.findViewById(R.id.ViewGenre);
        TextView year = listViewItem.findViewById(R.id.ViewYear);
        TextView rating = listViewItem.findViewById(R.id.ViewRating);
        ImageView image = listViewItem.findViewById(R.id.ViewImage);
        CardView cardView = listViewItem.findViewById(R.id.CardView);



        //Переход на детали фильма
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);
                try {
                    intent.putExtra("Id",(filmlist.get(position).getString("Id")));
                } catch (JSONException je) {
                    je.printStackTrace();
                }
                context.startActivity(intent);
            }
        });






        try{
            name.setText(filmlist.get(position).getString("nameOriginal"));
            year.setText(filmlist.get(position).getString("year"+", "+ "countries.country"));
            genre.setText(filmlist.get(position).getString("genres.genre"));
            rating.setText(filmlist.get(position).getString("ratingKinopoisk"));
            image.setImageResource(filmlist.get(position).getInt("posterUrlPreview"));
        }catch (JSONException je){
            je.printStackTrace();
        }
        return listViewItem;

    }
}
