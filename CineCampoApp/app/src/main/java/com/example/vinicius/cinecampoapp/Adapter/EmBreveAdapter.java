package com.example.vinicius.cinecampoapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinicius.cinecampoapp.FragEAtiv.EmBreves;
import com.example.vinicius.cinecampoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinicius on 12/1/16.
 */

public class EmBreveAdapter extends ArrayAdapter<EmBreve> {
    Context context;
    int resource;
    List<EmBreve> emBreve = new ArrayList<EmBreve>();

    public EmBreveAdapter(Context context, int resource, List<EmBreve> emBreve) {
        super(context, resource, emBreve);
        this.resource = resource;
        this.context = context;
        this.emBreve = emBreve;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        EmBreve item = emBreve.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textView_nome_filme);
            TextView Genero = (TextView) convertView.findViewById(R.id.textView_genero);
            ImageView Imagem_capa = (ImageView) convertView.findViewById(R.id.imageView_capa);
            Picasso.with(getContext()).load(item.getImagem_capa()).into(Imagem_capa);

            System.out.println(Nome.getText());
            System.out.println(Genero.getText());
            Nome.setText(item.getNome());
            Genero.setText(item.getGenero());

        }
        return convertView;
    }
}
