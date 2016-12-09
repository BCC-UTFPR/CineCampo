package com.example.vinicius.cinecampoapp.Adapter;

/**
 * Created by vinicius on 12/7/16.
 */

public class Comentario {
    String nome_user;
    String comentario;

    public Comentario(String nome_user, String comentario) {
        this.nome_user = nome_user;
        this.comentario = comentario;
    }

    public String getNome_user() {
        return nome_user;
    }

    public void setNome_user(String nome_user) {
        this.nome_user = nome_user;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
