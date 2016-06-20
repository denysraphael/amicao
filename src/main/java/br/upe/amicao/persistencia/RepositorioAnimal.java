/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.persistencia;

import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAnimal extends CrudRepository<Animal, Long> {

    //@Query("select a from Animal a where a.classificacao.nome=:nomeClassificacao")
    //public List<Animal> buscarPorClassificao(String nomeCategoria);

    //@Query("select a from Animal a where a.raca.nome=:nomeRaca")
    //public List<Animal> buscarPorRaca(String nomeRaca);
    
    /**
     * Procura Animal pelo Código
     * @param codigo
     * @return 
     */
    public Animal findByCodigo(Long codigo);
    
    /**
     * Procura Animais pelo Nome
     * @param nome
     * @return 
     */
    public List<Animal> findByNome(String nome);

    /**
     * Procura Animais pela Raça
     * @param raca
     * @return 
     */
    public List<Animal> findByRaca(Raca raca);
    
    /**
     * Procura Animais pelas Características
     * @param caracteristicas
     * @return 
     */
    public List<Animal> findByCaracteristicas(String caracteristicas);
    
    /**
     * Procura todos os Animais de um Usuário (dono)
     * @param dono
     * @return 
     */
    public List<Animal> findByDono(Usuario dono);
    
    /**
     * Lista todos os Animais
     * 
     * @return 
     */
    public List<Animal> findAllByOrderByCodigoAsc();
    
}
