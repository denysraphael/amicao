/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.persistencia;

import br.upe.amicao.entidades.Raca;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author -Denys
 */
@Repository
public interface RepositorioRaca extends CrudRepository<Raca, Long>{
    public Raca findByNome(String nome);
    @Query("select r from Raca r where r.classificacao.nome=:nomeClassificacao")
    public List<Raca> buscarPorClassificacao(String nomeClassificacao);
}
