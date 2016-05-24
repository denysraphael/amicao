/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.listar;

import br.upe.amicao.entidades.Adocao;
import java.util.Date;
import java.util.List;

public class ListarAdocao {
    
    private Long codigo;
    private String animal;
    private String anunciador;
    private String adotante;
    private List<String> interessados;
    private Date dataAnuncio;
    private Date dataInteressado;
    private boolean ativo; 

    public ListarAdocao(Long codigo, String animal, String anunciador, String adotante, List<String> interessados, Date dataAnuncio, Date dataInteressado, boolean ativo) {
        this.codigo = codigo;
        this.animal = animal;
        this.anunciador = anunciador;
        this.adotante = adotante;
        this.interessados = interessados;
        this.dataAnuncio = dataAnuncio;
        this.dataInteressado = dataInteressado;
        this.ativo = ativo;
    }

    public ListarAdocao() {
    }

     public ListarAdocao(Adocao a){
        this.codigo = a.getCodigo();
        this.animal = a.getAnimal().getNome();
        this.anunciador = a.getAnunciador().getNome();
        this.adotante = a.getAdotante().getNome();
        this.dataAnuncio = a.getDataAnuncio();
        this.dataInteressado = a.getDataInteressado();
        this.ativo = a.isAtivo();
    }
     
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getAnunciador() {
        return anunciador;
    }

    public void setAnunciador(String anunciador) {
        this.anunciador = anunciador;
    }

    public String getAdotante() {
        return adotante;
    }

    public void setAdotante(String adotante) {
        this.adotante = adotante;
    }

    public List<String> getInteressados() {
        return interessados;
    }

    public void setInteressados(List<String> interessados) {
        this.interessados = interessados;
    }

    public Date getDataAnuncio() {
        return dataAnuncio;
    }

    public void setDataAnuncio(Date dataAnuncio) {
        this.dataAnuncio = dataAnuncio;
    }

    public Date getDataInteressado() {
        return dataInteressado;
    }

    public void setDataInteressado(Date dataInteressado) {
        this.dataInteressado = dataInteressado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
   
}
