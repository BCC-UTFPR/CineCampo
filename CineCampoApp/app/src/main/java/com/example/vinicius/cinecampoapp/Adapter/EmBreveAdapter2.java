package com.example.vinicius.cinecampoapp.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vinicius.cinecampoapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by vinicius on 12/2/16.
 */

public class EmBreveAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<EmBreve> data= Collections.emptyList();
    EmBreve current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public EmBreveAdapter2(Context context, List<EmBreve> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.fragment_em_breve_item, parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        EmBreve current = data.get(position);
        myHolder.name.setText(current.nome);
        myHolder.genero.setText(current.genero);

//        // load image into imageview using glide
//        Glide.with(context).load("http://192.168.1.7/test/images/" + current.fishImage)
//                .placeholder(R.drawable.ic_img_error)
//                .error(R.drawable.ic_img_error)
//                .into(myHolder.ivFish);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView name;
        //ImageView ivFish;
        TextView genero;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_nome_filme);
            genero = (TextView) itemView.findViewById(R.id.textView_genero);
        }

    }

}
