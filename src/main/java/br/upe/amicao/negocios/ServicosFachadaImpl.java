/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.RacaExistenteException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AdocaoExistenteException;
import br.upe.amicao.exceptions.AnimalExistenteException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicosFachadaImpl implements ServicosFachada {

    @Autowired
    private ServicosUsuario servicoUsuarios;
    
    @Autowired
    private ServicosAdocao servicoAdocao;
    
    @Autowired
    private ServicosAnimal servicoAnimal;
    
    @Autowired
    private ServicosRaca servicoRaca;

    // ### USUÁRIO - START ###
    @Override
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException {
        servicoUsuarios.cadastrarUsuario(usuario);
    }

    @Override
    public void atualizarUsuario(Usuario usuario) throws UsuarioInexistenteException {
        servicoUsuarios.atualizarUsuario(usuario);
    }

    @Override
    public void desativarUsuario(Usuario usuario) throws UsuarioInexistenteException {
        servicoUsuarios.desativarUsuario(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return servicoUsuarios.listarUsuario();
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) throws UsuarioInexistenteException {
        return servicoUsuarios.buscarUsuarioPorEmail(email);
    }

    @Override
    public List<Usuario> buscarUsuarioPorNome(String nome) throws UsuarioInexistenteException {
        return servicoUsuarios.buscarUsuarioPorNome(nome);
    }
    // ### USUÁRIO - END ###

    // ### ADOÇÃO - START ###
    @Override
    public void cadastrarAdocao(Adocao adocao) throws AdocaoExistenteException {
        servicoAdocao.cadastrarAdocao(adocao);
    }
    
    @Override
    public void atualizarAdocao(Adocao adocao) throws AdocaoInexistenteException {
        servicoAdocao.atualizarAdocao(adocao);
    }

    @Override
    public void desativarAdocao(Adocao adocao) throws AdocaoInexistenteException {
        servicoAdocao.desativarAdocao(adocao);
    }

    @Override
    public List<Adocao> listarAdocoes() {
        return servicoAdocao.listarAdocoes();
    }
    
    @Override
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException {
        return servicoAdocao.buscarAdocaoPorCodigo(codigo);
    }

    @Override
    public List<Adocao> buscarAdocaoPorAnimal(Animal animal) throws AdocaoInexistenteException {
        return servicoAdocao.buscarAdocaoPorAnimal(animal);
    }
    
    @Override
    public List<Adocao> buscarAdocaoPorNomeAnimal(String nomeAnimal) throws AdocaoInexistenteException {
        return servicoAdocao.buscarAdocaoPorNomeAnimal(nomeAnimal);
    }

    @Override
    public List<Adocao> buscarAdocaoPorRaca(Raca raca) throws AdocaoInexistenteException {
        return servicoAdocao.buscarAdocaoPorRaca(raca);
    }

    @Override
    public List<Adocao> buscarAdocaoPorAnunciador(Usuario anunciador) throws AdocaoInexistenteException {
        return servicoAdocao.buscarAdocaoPorAnunciador(anunciador);
    }

    @Override
    public List<Adocao> buscarAdocaoPorAdotante(Usuario adotante) throws AdocaoInexistenteException {
        return servicoAdocao.buscarAdocaoPorAdotante(adotante);
    }

    /*@Override
    public List<ListarAdocao> buscarPorData(Date data) {
        return servicoAdocao.buscarPorData(data);
    }

    @Override
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException {
        return servicoAdocao.buscarAdocaoPorCodigo(codigo);
    }

    @Override
    public void interesseAdocao(String email, long codigo) throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoJaRealizadaException {
        servicoAdocao.interesseAdocao(email, codigo);
    }

    @Override
    public void escolherAdotante(Long codigo, String nomeAdotante) throws AdocaoInexistenteException, UsuarioNaoInteressadoExceptions {
        servicoAdocao.escolherAdotante(codigo, nomeAdotante);
    }*/
    // ### ADOÇÃO - END ###

    // ### ANIMAL - START ###
    @Override
    public void cadastrarAnimal(Animal animal) throws AnimalExistenteException {
        servicoAnimal.cadastrarAnimal(animal);
    }

    @Override
    public void atualizarAnimal(Animal animal) throws AnimalInexistenteException {
        servicoAnimal.atualizarAnimal(animal);
    }

    @Override
    public void deletarAnimal(Long animal) throws AnimalInexistenteException {
        servicoAnimal.deletarAnimal(animal);
    }

    @Override
    public Animal buscarAnimalPorCodigo(Long codigo) throws AnimalInexistenteException {
        return servicoAnimal.buscarAnimalPorCodigo(codigo);
    }

    @Override
    public List<Animal> listarAnimal() {
        return servicoAnimal.listarAnimal();
    }

    @Override
    public List<Animal> buscarAnimalPorNome(String nome) throws AnimalInexistenteException {
        return servicoAnimal.buscarAnimalPorNome(nome);
    }

    @Override
    public List<Animal> buscarAnimalPorRaca(Raca raca) throws AnimalInexistenteException {
        return servicoAnimal.buscarAnimalPorRaca(raca);
    }
    
    @Override
    public List<Animal> buscarAnimalClassificacao(String classificacao) throws AnimalInexistenteException {
        return servicoAnimal.buscarAnimalClassificacao(classificacao);
    }
    // ### ANIMAL - END ###

    // ### RAçA - START ###
    @Override
    public void cadastrarRaca(Raca raca) throws RacaExistenteException {
        servicoRaca.cadastrarRaca(raca);
    }

    @Override
    public void atualizarRaca(Raca raca) throws RacaInexistenteException {
        servicoRaca.atualizarRaca(raca);
    }

    @Override
    public List<Raca> listarRaca() {
        return servicoRaca.listarRaca();
    }

    @Override
    public Raca buscarRacaPorNome(String nome) throws RacaInexistenteException {
        return servicoRaca.buscarRacaPorNome(nome);
    }

    @Override
    public List<Raca> buscarRacaPorClassificacao(String classificacaoNome) {
        return servicoRaca.buscarRacaPorClassificacao(classificacaoNome);
    }
    // ### RAçA - END ###
}
