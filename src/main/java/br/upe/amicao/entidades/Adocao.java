/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Adocao implements Serializable{
    
    private Long codigo;
    private String descricao;
    private Animal animal;
    private Usuario anunciador;
    private Usuario adotante;
    private boolean ativo;

    public Adocao() {
    }

    public Adocao(String descricao, Animal animal, Usuario anunciador) {
        this.descricao = descricao;
        this.animal = animal;
        this.anunciador = anunciador;
        this.ativo = true;
    }

    public Adocao(String descricao, Animal animal, Usuario anunciador, Usuario adotante, boolean ativo) {
        this.descricao = descricao;
        this.animal = animal;
        this.anunciador = anunciador;
        this.adotante = adotante;
        this.ativo = ativo;
    }

    public Adocao(Long codigo, String descricao, Animal animal, Usuario anunciador, Usuario adotante, boolean ativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.animal = animal;
        this.anunciador = anunciador;
        this.adotante = adotante;
        this.ativo = ativo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long Codigo) {
        this.codigo = Codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Usuario getAnunciador() {
        return anunciador;
    }

    public void setAnunciador(Usuario anunciador) {
        this.anunciador = anunciador;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Usuario getAdotante() {
        return adotante;
    }

    public void setAdotante(Usuario adotante) {
        this.adotante = adotante;
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
        hash = 71 * hash + Objects.hashCode(this.codigo);
        hash = 71 * hash + Objects.hashCode(this.descricao);
        hash = 71 * hash + Objects.hashCode(this.animal);
        hash = 71 * hash + Objects.hashCode(this.anunciador);
        hash = 71 * hash + Objects.hashCode(this.adotante);
        hash = 71 * hash + (this.ativo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Adocao other = (Adocao) obj;
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.animal, other.animal)) {
            return false;
        }
        if (!Objects.equals(this.anunciador, other.anunciador)) {
            return false;
        }
        if (!Objects.equals(this.adotante, other.adotante)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adocao{" + "codigo=" + codigo + ", descricao=" + descricao + ", animal=" + animal + ", anunciador=" + anunciador + ", adotante=" + adotante + ", ativo=" + ativo + '}';
    }
}
