package com.example.vinicius.cinecampoapp.Adapter;

/**
 * Created by vinicius on 12/1/16.
 */

public class EmExibicao {
    int id;
    String nome;
    String duracao;
    String genero;
    String sinopse;
    String d3;
    String sessao;
    int avaliacao;
    String videourl;
    String imagemurl;

    public EmExibicao(int id, String nome, String duracao, String genero, String sinopse, String d3, String sessao, int avaliacao, String videourl, String imagemurl) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.genero = genero;
        this.sinopse = sinopse;
        this.d3 = d3;
        this.sessao = sessao;
        this.avaliacao = avaliacao;
        this.videourl = videourl;
        this.imagemurl = imagemurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getImagemurl() {
        return imagemurl;
    }

    public void setImagemurl(String imagemurl) {
        this.imagemurl = imagemurl;
    }
}
