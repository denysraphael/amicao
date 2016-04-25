/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.listar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author -Denys
 */
public class ListarAdocao {
    
     private Long codigo;
    private String animal;
    private String usuarioAnunciador;
    private String usuarioEscolhido;
    private List<String> interessados;
    private Date dataAnuncio;
    private Date dataInteressado;
    private boolean ativo; 

    public ListarAdocao(Long codigo, String animal, String usuarioAnunciador, String usuarioEscolhido, List<String> interessados, Date dataAnuncio, Date dataInteressado, boolean ativo) {
        this.codigo = codigo;
        this.animal = animal;
        this.usuarioAnunciador = usuarioAnunciador;
        this.usuarioEscolhido = usuarioEscolhido;
        this.interessados = interessados;
        this.dataAnuncio = dataAnuncio;
        this.dataInteressado = dataInteressado;
        this.ativo = ativo;
    }

    public ListarAdocao() {
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

    public String getUsuarioAnunciador() {
        return usuarioAnunciador;
    }

    public void setUsuarioAnunciador(String usuarioAnunciador) {
        this.usuarioAnunciador = usuarioAnunciador;
    }

    public String getUsuarioEscolhido() {
        return usuarioEscolhido;
    }

    public void setUsuarioEscolhido(String usuarioEscolhido) {
        this.usuarioEscolhido = usuarioEscolhido;
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
