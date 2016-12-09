package com.example.vinicius.cinecampoapp.Adapter;

/**
 * Created by vinicius on 12/1/16.
 */

public class EmBreve {
    public String nome;
    public String sinopse;
    public String duracao;
    public String genero;
    public String imagem_capa;
    public String videoUrl;

    public EmBreve(String nome, String sinopse, String duracao, String genero, String imagem_capa, String videoUrl){
        this.nome = nome;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.genero = genero;
        this.imagem_capa = imagem_capa;
        this.videoUrl = videoUrl;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagem_capa() {
        return imagem_capa;
    }

    public void setImagem_capa(String imagem_capa) {
        this.imagem_capa = imagem_capa;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
