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
import br.upe.amicao.exceptions.RacaExistenteException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.persistencia.RepositorioAnimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void cadastrarAnimal(Animal animal, String raca, String classificacao) throws AnimalExistenteException {
        try {
            Animal temp = this.buscarAnimalPorDono(animal.getDono(), animal.getNome());

            if (temp == null) {
                throw new AnimalExistenteException();
            }
        } catch (AnimalInexistenteException e) {
            try {
                Raca r = this.servicosRaca.buscarRacaPorNome(raca);

                animal.setRaca(r);
                this.repAnimal.save(animal);
            } catch (RacaInexistenteException ex) {
                try {
                    Raca r = new Raca(raca, classificacao);
                    this.servicosRaca.cadastrarRaca(r);
                    Raca r2 = this.servicosRaca.buscarRacaPorNome(r.getNome());

                    animal.setRaca(r2);
                    this.repAnimal.save(animal);
                } catch (RacaExistenteException | RacaInexistenteException ex1) {
                    Logger.getLogger(ServicosAnimalImpl.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
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
        Animal a = repAnimal.findOne(codigo);

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
    public Animal buscarAnimalPorDono(Usuario dono, String nomeAnimal) throws AnimalInexistenteException {
        Animal temp = repAnimal.findAnimalPorDonoENome(dono, nomeAnimal);

        if (temp == null) {
            throw new AnimalInexistenteException();
        }

        return temp;
    }
    
    @Override
    public List<Animal> buscarAnimalPorCaracteristicas(String caracteristicas) throws AnimalInexistenteException {
        List<Animal> temp = repAnimal.findByCaracteristicas(caracteristicas);
        
        if (temp.isEmpty()) {
            throw new AnimalInexistenteException();
        }
        
        return temp;
    }

    @Override
    public List<Animal> buscarAnimalPorRaca(Raca raca) throws AnimalInexistenteException {
        return (List<Animal>) repAnimal.findByRaca(raca);
    }

    @Override
    public List<Animal> buscarAnimalClassificacao(String classificacao) throws AnimalInexistenteException {
        return (List<Animal>) repAnimal.findByCaracteristicas(classificacao);
    }
}
