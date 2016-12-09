package com.example.vinicius.cinecampoapp.Adapter;

/**
 * Created by vinicius on 12/6/16.
 */

public class Login {
    String usuario;
    String senha;

    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
