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
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.persistencia.RepositorioAnimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author -Denys
 */
@Service
public class ServicosAnimalImpl implements ServicosAnimal {

    @Autowired
    private RepositorioAnimal repositorioAnimal;
    @Autowired
    private ServicosClassificacao servicosClassificacao;
    @Autowired
    private ServicosRaca servicosRaca;
    
    
    @Override
    @Transactional(rollbackFor = ClassificacaoInexistenteException.class)
    public void cadastrarAnimal(Animal animal, String classificacaoNome, String racaNome) throws ClassificacaoInexistenteException, RacaInexistenteException {
        animal.setClassificacao(servicosClassificacao.buscarClassificacaoPorNome(classificacaoNome));
        animal.setRaca(servicosRaca.buscarRacaPorNome(racaNome));
        Animal a = repositorioAnimal.save(animal);
        animal.setCodigo(a.getCodigo());
    }

    @Override
    @Transactional(rollbackFor = AnimalInexistenteException.class)
    public void atualizarAnimal(Animal animal, Long codigoAtualizado, String classificacaoNome, String racaNome) throws AnimalInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException {
        Animal animalAtualizar = repositorioAnimal.findOne(animal.getCodigo());
        if(animalAtualizar==null){
            throw new AnimalInexistenteException();
        }
        animal.setClassificacao(servicosClassificacao.buscarClassificacaoPorNome(classificacaoNome));
        animal.setRaca(servicosRaca.buscarRacaPorNome(racaNome));
        animal.setCodigo(animalAtualizar.getCodigo());
        repositorioAnimal.save(animal);
    }

    @Override
    @Transactional(rollbackFor = AnimalInexistenteException.class)
    public void deletarAnimal(Long codigo) throws AnimalInexistenteException {
        repositorioAnimal.delete(codigo);
    }

    @Override
    public Animal consultarAnimalPorCodigo(Long codigo) throws AnimalInexistenteException {
        return repositorioAnimal.findOne(codigo);
    }

    @Override
    public List<Animal> listarAnimal() {
        return (List<Animal>) repositorioAnimal.findAll();
    }

    @Override
    public List<Animal> buscarAnimalPorNome(String nome) {
        return (List<Animal>) repositorioAnimal.findByNome(nome);
    }

    @Override
    public List<Animal> buscarAnimalPorClassificacao(String nomeClassificacao) {
        return(List<Animal>) repositorioAnimal.buscarPorClassificao(nomeClassificacao);
    }

    @Override
    public List<Animal> buscarAnimalPorRaca(String nomeRaca) {
        return(List<Animal>) repositorioAnimal.buscarPorRaca(nomeRaca);
    }
}
