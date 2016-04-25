/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.listar;

import java.util.List;

/**
 *
 * @author -Denys
 */
public class ListarUsuario {
    private Long codigo;
    private String email;
    private String nome;
    private String senha;
    private String telefone;
    private List<String> animais;
    private List<String> adocoesAnunciadas;
    private List<String> adocoesInteressadas;
    private List<String> adocoesRealizadas;
    private boolean ativo;

    public ListarUsuario(Long codigo, String email, String nome, String senha, String telefone, List<String> animais, List<String> adocoesAnunciadas, List<String> adocoesInteressadas, List<String> adocoesRealizadas, boolean ativo) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.animais = animais;
        this.adocoesAnunciadas = adocoesAnunciadas;
        this.adocoesInteressadas = adocoesInteressadas;
        this.adocoesRealizadas = adocoesRealizadas;
        this.ativo = ativo;
    }

    public ListarUsuario() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getAnimais() {
        return animais;
    }

    public void setAnimais(List<String> animais) {
        this.animais = animais;
    }

    public List<String> getAdocoesAnunciadas() {
        return adocoesAnunciadas;
    }

    public void setAdocoesAnunciadas(List<String> adocoesAnunciadas) {
        this.adocoesAnunciadas = adocoesAnunciadas;
    }

    public List<String> getAdocoesInteressadas() {
        return adocoesInteressadas;
    }

    public void setAdocoesInteressadas(List<String> adocoesInteressadas) {
        this.adocoesInteressadas = adocoesInteressadas;
    }

    public List<String> getAdocoesRealizadas() {
        return adocoesRealizadas;
    }

    public void setAdocoesRealizadas(List<String> adocoesRealizadas) {
        this.adocoesRealizadas = adocoesRealizadas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    
   
    
}
