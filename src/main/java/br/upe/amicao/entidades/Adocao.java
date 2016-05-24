/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.entidades;

import java.io.Serializable;
import java.util.Date;
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
    private Animal animal;
    private Usuario anunciador;
    private Usuario adotante;
    private List<Usuario> interessados;
    private Date dataAnuncio;
    private Date dataInteressado;
    private boolean ativo; 
    
    public Adocao(Animal animal, Date dataAnuncio, List<Usuario> interessados,Usuario adotado, Date dataInteressado) {
        this.animal = animal;
        this.interessados = interessados;
        this.adotante = adotante;
        this.dataInteressado = dataInteressado;
    }

    public Adocao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long Codigo) {
        this.codigo = Codigo;
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

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Usuario> getInteressados() {
        return interessados;
    }

    public void setInteressados(List<Usuario> interessados) {
        this.interessados = interessados;
    }

    @Temporal(TemporalType.DATE)
    public Date getDataAnuncio() {
        return dataAnuncio;
    }

    public void setDataAnuncio(Date dataAnuncio) {
        this.dataAnuncio = dataAnuncio;
    }

    @Temporal(TemporalType.DATE)
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.codigo);
        hash = 67 * hash + Objects.hashCode(this.animal);
        hash = 67 * hash + Objects.hashCode(this.anunciador);
        hash = 67 * hash + Objects.hashCode(this.adotante);
        hash = 67 * hash + Objects.hashCode(this.interessados);
        hash = 67 * hash + Objects.hashCode(this.dataAnuncio);
        hash = 67 * hash + Objects.hashCode(this.dataInteressado);
        hash = 67 * hash + (this.ativo ? 1 : 0);
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
        final Adocao other = (Adocao) obj;
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
        if (!Objects.equals(this.interessados, other.interessados)) {
            return false;
        }
        if (!Objects.equals(this.dataAnuncio, other.dataAnuncio)) {
            return false;
        }
        if (!Objects.equals(this.dataInteressado, other.dataInteressado)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adocao{" + "codigo=" + codigo + ", animal=" + animal + ", anunciador=" + anunciador + ", adotante=" + adotante + ", interessados=" + interessados + ", dataAnuncio=" + dataAnuncio + ", dataInteressado=" + dataInteressado + ", ativo=" + ativo + '}';
    }
}
