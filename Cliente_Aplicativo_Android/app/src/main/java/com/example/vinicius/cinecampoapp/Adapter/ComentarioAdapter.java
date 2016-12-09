package com.example.vinicius.cinecampoapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinicius.cinecampoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinicius on 12/7/16.
 */

public class ComentarioAdapter extends ArrayAdapter<Comentario> {
    Context context;
    int resource;
    List<Comentario> comentario = new ArrayList<Comentario>();

    public ComentarioAdapter(Context context, int resource, List<Comentario> comentario) {
        super(context, resource, comentario);
        this.resource = resource;
        this.context = context;
        this.comentario = comentario;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Comentario item = comentario.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textView_usuario);
            TextView Comentario = (TextView) convertView.findViewById(R.id.textView_comentario);

            Nome.setText(item.getNome_user());
            Comentario.setText(item.getComentario());
        }
        return convertView;
    }
}
