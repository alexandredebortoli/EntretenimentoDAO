package model;

public class Jogo {
    private int id;
    private int ano;
    private String titulo;
    private String categoria;
    private String sinopse;
    private int ativo = 1;

    public Jogo() {}

    public Jogo(int ano, String titulo, String categoria, String sinopse) {
        this.ano = ano;
        this.titulo = titulo;
        this.categoria = categoria;
        this.sinopse = sinopse;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSinopse() {
        return this.sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getAtivo() {
        return this.ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
}
