/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

    private Long codigo;
    private String email;
    private String nome;
    private String senha;
    private String telefone;
    private List<Animal> animais;
    private List<Adocao> adocoesAnunciadas;
    private List<Adocao> adocoesInteressadas;
    private List<Adocao> adocoesRealizadas;
    private boolean ativo;
    
    public Usuario() {
        this.ativo = true;
    }
    
    public Usuario(String email, String nome, String senha, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.ativo = true;
        this.animais = new ArrayList<Animal>();
        this.adocoesAnunciadas = new ArrayList<Adocao>();
        this.adocoesInteressadas = new ArrayList<Adocao>();
        this.adocoesRealizadas = new ArrayList<Adocao>();
    }

    public Usuario(String email, String nome, String senha, String telefone, List<Animal> animais, List<Adocao> adocoesAnunciadas, List<Adocao> adocoesInteressadas, List<Adocao> adocoesRealizadas, boolean ativo) {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dono")
    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "anunciador")
    public List<Adocao> getAdocoesAnunciadas() {
        return adocoesAnunciadas;
    }

    public void setAdocoesAnunciadas(List<Adocao> adocoesAnunciadas) {
        this.adocoesAnunciadas = adocoesAnunciadas;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "interessados")
    public List<Adocao> getAdocoesInteressadas() {
        return adocoesInteressadas;
    }

    public void setAdocoesInteressadas(List<Adocao> adocoesInteressadas) {
        this.adocoesInteressadas = adocoesInteressadas;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "adotante")
    public List<Adocao> getAdocoesRealizadas() {
        return adocoesRealizadas;
    }

    public void setAdocoesRealizadas(List<Adocao> adocoesRealizadas) {
        this.adocoesRealizadas = adocoesRealizadas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.codigo);
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.senha);
        hash = 23 * hash + Objects.hashCode(this.telefone);
        hash = 23 * hash + Objects.hashCode(this.animais);
        hash = 23 * hash + Objects.hashCode(this.adocoesAnunciadas);
        hash = 23 * hash + Objects.hashCode(this.adocoesInteressadas);
        hash = 23 * hash + Objects.hashCode(this.adocoesRealizadas);
        hash = 23 * hash + (this.ativo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.animais, other.animais)) {
            return false;
        }
        if (!Objects.equals(this.adocoesAnunciadas, other.adocoesAnunciadas)) {
            return false;
        }
        if (!Objects.equals(this.adocoesInteressadas, other.adocoesInteressadas)) {
            return false;
        }
        if (!Objects.equals(this.adocoesRealizadas, other.adocoesRealizadas)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", email=" + email + ", nome=" + nome + ", senha=" + senha + ", telefone=" + telefone + ", animais=" + animais + ", adocoesAnunciadas=" + adocoesAnunciadas + ", adocoesInteressadas=" + adocoesInteressadas + ", adocoesRealizadas=" + adocoesRealizadas + ", ativo=" + ativo + '}';
    }
}
