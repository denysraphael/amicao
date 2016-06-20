/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AnimalExistenteException;
import br.upe.amicao.persistencia.RepositorioAnimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicosAnimalImpl implements ServicosAnimal {

    @Autowired
    private RepositorioAnimal repAnimal;

    @Autowired
    private ServicosRaca servicosRaca;

    @Override
    @Transactional(rollbackFor = AnimalInexistenteException.class)
    public void cadastrarAnimal(Animal animal) throws AnimalExistenteException {
        try {
            List<Animal> list = this.findByDono(animal.getDono());

            if (!list.isEmpty()) {
                throw new AnimalExistenteException();
            }
        } catch (AnimalInexistenteException e) {
            repAnimal.save(animal);
        }
    }

    @Override
    @Transactional(rollbackFor = {AnimalInexistenteException.class})
    public void atualizarAnimal(Animal animal) throws AnimalInexistenteException {
        Animal animalAtualizar = this.buscarAnimalPorCodigo(animal.getCodigo());

        if (animalAtualizar != null) {
            animalAtualizar.setNome(animal.getNome());
            animalAtualizar.setRaca(animal.getRaca());
            animalAtualizar.setDono(animal.getDono());
            animalAtualizar.setCaracteristicas(animal.getCaracteristicas());
            animalAtualizar.setDataNascimento(animal.getDataNascimento());

            repAnimal.save(animalAtualizar);
        }
    }

    // VER ISSO AQUI
    @Override
    @Transactional(rollbackFor = AnimalInexistenteException.class)
    public void deletarAnimal(Long codigo) throws AnimalInexistenteException {
        repAnimal.delete(codigo);
    }
    // VER ISSO AQUI
    
    @Override
    public Animal buscarAnimalPorCodigo(Long codigo) throws AnimalInexistenteException {
        Animal a = repAnimal.findByCodigo(codigo);
        
        if (a == null) {
            throw new AnimalInexistenteException();
        }
        
        return a;
    }

    @Override
    public List<Animal> listarAnimal() {
        return (List<Animal>) repAnimal.findAllByOrderByCodigoAsc();
    }

    @Override
    public List<Animal> buscarAnimalPorNome(String nome) throws AnimalInexistenteException {
        return (List<Animal>) repAnimal.findByNome(nome);
    }

    @Override
    public List<Animal> buscarAnimalPorRaca(Raca raca) throws AnimalInexistenteException {
        return (List<Animal>) repAnimal.findByRaca(raca);
    }
    
    @Override
    public List<Animal> buscarAnimalClassificacao(String classificacao) throws AnimalInexistenteException {
        return (List<Animal>) repAnimal.findByCaracteristicas(classificacao);
    }


    private List<Animal> findByDono(Usuario dono) throws AnimalInexistenteException {
        List<Animal> temp = repAnimal.findByDono(dono);

        if (temp.isEmpty()) {
            throw new AnimalInexistenteException();
        }

        return temp;
    }
}
