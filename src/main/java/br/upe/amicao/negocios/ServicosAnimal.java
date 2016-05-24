/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.entidades.Animal;
import java.io.Serializable;
import java.util.List;

public interface ServicosAnimal extends Serializable {
    public void cadastrarAnimal(Animal animal, String classificacaoNome, String racaNome)throws ClassificacaoInexistenteException, RacaInexistenteException;
    public void atualizarAnimal(Animal animal, Long codigoAtualizado, String classificacaoNome, String racaNome) throws AnimalInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException;
    public void deletarAnimal(Long animal)throws AnimalInexistenteException;
    public Animal consultarAnimalPorCodigo(Long codigo)throws AnimalInexistenteException;;
    public List<Animal> listarAnimal();
    public List<Animal> buscarAnimalPorNome(String nome);
    public List<Animal> buscarAnimalPorClassificacao(String nomeClassificacao);
    public List<Animal> buscarAnimalPorRaca(String nomeRaca);
}
