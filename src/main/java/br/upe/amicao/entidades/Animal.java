/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Animal implements Serializable {

    private Long codigo;
    private String nome;
    private Raca raca;
    private String caracteristicas;
    private Date dataNascimento;
    private Usuario dono;

    public Animal() {
    }

    public Animal(Raca raca, String nome, String caracteristicas, Date dataNascimento, Usuario dono) {
        this.raca = raca;
        this.nome = nome;
        this.caracteristicas = caracteristicas;
        this.dataNascimento = dataNascimento;
        this.dono = dono;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Temporal(TemporalType.DATE)
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @ManyToOne
    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.codigo);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.raca);
        hash = 47 * hash + Objects.hashCode(this.caracteristicas);
        hash = 47 * hash + Objects.hashCode(this.dataNascimento);
        hash = 47 * hash + Objects.hashCode(this.dono);
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
        final Animal other = (Animal) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.caracteristicas, other.caracteristicas)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.raca, other.raca)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        if (!Objects.equals(this.dono, other.dono)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Animal{" + "codigo=" + codigo + ", nome=" + nome + ", raca=" + raca + ", caracteristicas=" + caracteristicas + ", dataNascimento=" + dataNascimento + ", dono=" + dono + '}';
    }
}
