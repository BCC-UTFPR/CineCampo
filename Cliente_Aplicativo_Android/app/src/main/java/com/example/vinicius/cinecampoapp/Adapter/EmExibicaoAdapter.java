package com.example.vinicius.cinecampoapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.vinicius.cinecampoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinicius on 12/1/16.
 */

public class EmExibicaoAdapter extends ArrayAdapter<EmExibicao> {
    Context context;
    int resource;
    List<EmExibicao> emExibicao = new ArrayList<EmExibicao>();

    public EmExibicaoAdapter(Context context, int resource, List<EmExibicao> emExibicao) {
        super(context, resource, emExibicao);
        this.resource = resource;
        this.context = context;
        this.emExibicao = emExibicao;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        EmExibicao item = emExibicao.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textView_nome_filme);
            TextView Sessao = (TextView) convertView.findViewById(R.id.textView_sessao);
            TextView Genero = (TextView) convertView.findViewById(R.id.textView_genero);
            RatingBar nota = (RatingBar) convertView.findViewById(R.id.ratingBar);
            nota.setRating(Float.parseFloat(String.valueOf(item.getAvaliacao())));
            ImageView Imagem_capa = (ImageView) convertView.findViewById(R.id.imageView_capa);
            Picasso.with(getContext()).load(item.getImagemurl()).into(Imagem_capa);

            Nome.setText(item.getNome());
            Sessao.setText(item.getSessao());
            Genero.setText(item.getGenero());

        }
        return convertView;
    }
}
