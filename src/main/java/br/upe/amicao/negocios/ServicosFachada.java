/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AdocaoExistenteException;
import br.upe.amicao.exceptions.AnimalExistenteException;
import br.upe.amicao.exceptions.RacaExistenteException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ServicosFachada extends Serializable {

    //Usuario
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException;

    public void atualizarUsuario(Usuario usuario) throws UsuarioInexistenteException;

    public void desativarUsuario(Usuario usuario) throws UsuarioInexistenteException;

    public List<Usuario> listarUsuario();

    public Usuario buscarUsuarioPorEmail(String email) throws UsuarioInexistenteException;

    public List<Usuario> buscarUsuarioPorNome(String nome) throws UsuarioInexistenteException;

    //Adocao
    public void cadastrarAdocao(Adocao adocao) throws AdocaoExistenteException;
    
    public void atualizarAdocao(Adocao adocao) throws AdocaoInexistenteException;

    public void desativarAdocao(Adocao adocao) throws AdocaoInexistenteException;

    public List<Adocao> listarAdocoes();
    
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException;

    public List<Adocao> buscarAdocaoPorAnimal(Animal animal) throws AdocaoInexistenteException;
    
    public List<Adocao> buscarAdocaoPorNomeAnimal(String nomeAnimal) throws AdocaoInexistenteException;
    
    public List<Adocao> buscarAdocaoPorRaca(Raca raca) throws AdocaoInexistenteException;

    //public List<ListarAdocao> buscarAdocaoPorClassificacao(String nomeClassificacao);

    //public List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca);

    public List<Adocao> buscarAdocaoPorAnunciador(Usuario anunciador) throws AdocaoInexistenteException;

    public List<Adocao> buscarAdocaoPorAdotante(Usuario adotante) throws AdocaoInexistenteException;

    //public List<ListarAdocao> buscarPorData(Date data);

    //public void interesseAdocao(String email, long codigo) throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoJaRealizadaException;

    //public void escolherAdotante(Long codigo, String nomeAdotante) throws AdocaoInexistenteException, UsuarioNaoInteressadoExceptions;

    //Animal
    public void cadastrarAnimal(Animal animal) throws AnimalExistenteException;

    public void atualizarAnimal(Animal animal) throws AnimalInexistenteException;

    public void deletarAnimal(Long animal) throws AnimalInexistenteException;

    public Animal buscarAnimalPorCodigo(Long codigo) throws AnimalInexistenteException;

    public List<Animal> listarAnimal();

    public List<Animal> buscarAnimalPorNome(String nome) throws AnimalInexistenteException;
    
    public List<Animal> buscarAnimalPorRaca(Raca raca) throws AnimalInexistenteException;

    public List<Animal> buscarAnimalClassificacao(String classificacao) throws AnimalInexistenteException;

    //Raca
    public void cadastrarRaca(Raca raca) throws RacaExistenteException;

    public void atualizarRaca(Raca raca) throws RacaInexistenteException;

    public List<Raca> listarRaca();

    public Raca buscarRacaPorNome(String nome) throws RacaInexistenteException;

    public List<Raca> buscarRacaPorClassificacao(String classificacaoNome);

}
