/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.persistencia;

import br.upe.amicao.entidades.Animal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author -Denys
 */
@Repository
public interface RepositorioAnimal extends CrudRepository<Animal, Long> {
    @Query("select a from Animal a where a.classificacao.nome=:nomeClassificacao")
    public List<Animal> buscarPorClassificao(String nomeCategoria);
    @Query("select a from Animal a where a.raca.nome=:nomeRaca")
    public List<Animal> buscarPorRaca(String nomeRaca);
    public List<Animal> findByNome(String nome);
}
