/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.exceptions.AnimalExistenteException;
import java.io.Serializable;
import java.util.List;

public interface ServicosAnimal extends Serializable {

    /**
     * Cadastrar um Animal
     * 
     * @param animal
     * @throws AnimalExistenteException 
     */
    public void cadastrarAnimal(Animal animal) throws AnimalExistenteException;

    /**
     * Atualizar um Animal
     * 
     * @param animal
     * @throws AnimalInexistenteException 
     */
    public void atualizarAnimal(Animal animal) throws AnimalInexistenteException;

    /**
     * Deletar um Animal
     * 
     * @param animal
     * @throws AnimalInexistenteException 
     */
    public void deletarAnimal(Long animal) throws AnimalInexistenteException;

    /**
     * Buscar um Animal pelo Código
     * 
     * @param codigo
     * @return
     * @throws AnimalInexistenteException 
     */
    public Animal buscarAnimalPorCodigo(Long codigo) throws AnimalInexistenteException;
    
    /**
     * Listar todos os Animais
     * 
     * @return 
     */
    public List<Animal> listarAnimal();

    /**
     * Buscar uma lista de Animais pelo Nome
     * 
     * @param nome
     * @return
     * @throws AnimalInexistenteException 
     */
    public List<Animal> buscarAnimalPorNome(String nome) throws AnimalInexistenteException;

    /**
     * Buscar Animais pela Raça
     * 
     * @param raca
     * @return
     * @throws AnimalInexistenteException 
     */
    public List<Animal> buscarAnimalPorRaca(Raca raca) throws AnimalInexistenteException;
    
    /**
     * Buscar Animais pela Classificação
     * 
     * @param classificacao
     * @return
     * @throws AnimalInexistenteException 
     */
    public List<Animal> buscarAnimalClassificacao(String classificacao) throws AnimalInexistenteException;
}
