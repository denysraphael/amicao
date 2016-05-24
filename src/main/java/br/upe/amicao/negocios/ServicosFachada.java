/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.listar.ListarAdocao;
import br.upe.amicao.listar.ListarUsuario;
import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.ProprioUsuarioAnunciadorException;
import br.upe.amicao.exceptions.RacaExistenteException;
import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
import br.upe.amicao.exceptions.ClassificacaoExistenteException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Classificacao;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AdocaoJaRealizadaException;
import br.upe.amicao.exceptions.UsuarioNaoInteressadoExceptions;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ServicosFachada extends Serializable{
    
    //Usuario
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException, UsuarioInexistenteException;
    public void atualizarUsuario(Usuario usuario, String emailAtualizar) throws UsuarioInexistenteException;
    public void excluirUsuario(String email) throws UsuarioInexistenteException;
    public List<ListarUsuario> listarUsuario();
    public Usuario BuscarUsuarioPorEmail(String email) throws UsuarioInexistenteException;
    public List<ListarUsuario> BuscarUsuarioPorNome(String nome) throws UsuarioInexistenteException;
    
    //Adocao
   public void cadastrarAdocao(Adocao adocao, Long codigoAnimal, String email) throws UsuarioInexistenteException;
    public void excluirAdocao(Long codigo) throws AdocaoInexistenteException;
    public List<ListarAdocao> listarAdocao();
    public List<ListarAdocao> buscarAdocaoPorAnimal(String nomeAnimal);
    public List<ListarAdocao> buscarAdocaoPorClassificacao(String nomeClassificacao);
    public List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca);
    public List<ListarAdocao> buscarAdocaoPorAnunciador(String nomeAnunciador);
    public List<ListarAdocao> buscarAdocaoPorAdotante(String nomeAdotante);
    public List<ListarAdocao> buscarPorData(Date data);
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException;
    public void interesseAdocao(String email,long codigo)throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoJaRealizadaException;
    public void escolherAdotante(Long codigo, String nomeAdotante) throws AdocaoInexistenteException, UsuarioNaoInteressadoExceptions;    
    
    //Animal
    public void cadastrarAnimal(Animal animal, String classificacaoNome, String racaNome)throws ClassificacaoInexistenteException, RacaInexistenteException;
    public void atualizarAnimal(Animal animal, Long codigoAtualizado, String classificacaoNome, String racaNome) throws AnimalInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException;
    public void deletarAnimal(Long animal)throws AnimalInexistenteException;
    public Animal consultarAnimalPorCodigo(Long codigo)throws AnimalInexistenteException;;
    public List<Animal> listarAnimal();
    public List<Animal> buscarAnimalPorNome(String nome);
    public List<Animal> buscarAnimalPorClassificacao(String nomeClassificacao);
    public List<Animal> buscarAnimalPorRaca(String nomeRaca);
    
    //Classificacao
    public void cadastrarClassificacao(Classificacao classificacao) throws ClassificacaoExistenteException;
    public void atualizarClassificacao(String nomeAtual, String nomeAtualizar) throws ClassificacaoInexistenteException;
    public List<Classificacao> listarClassificacao();
    public Classificacao buscarClassificacaoPorNome(String nome) throws ClassificacaoInexistenteException;
    
    //Raca
    public void cadastrarRaca(Raca raca, String classificacaoNome) throws RacaExistenteException, ClassificacaoInexistenteException;
    public void atualizarRaca(String nomeAtual, String nomeAtualizar, String classificacaoNome) throws RacaInexistenteException, ClassificacaoInexistenteException;
    public List<Raca> listarRaca();
    public Raca buscarRacaPorNome(String nome) throws RacaInexistenteException;
    public List<Raca> buscarRacaPorClassificacao(String classificacaoNome);
    
}
