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
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author -Denys
 */
@Service
public class ServicosFachadaImpl implements ServicosFachada{

    @Autowired
    private ServicosUsuario servicoUsuarios;
    @Autowired
    private ServicosAdocao servicoAdocao;
    @Autowired
    private ServicosAnimal servicoAnimal;
    @Autowired
    private ServicosClassificacao servicoClassificacao;
    @Autowired
    private ServicosRaca servicoRaca;
    
    @Override
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException, UsuarioInexistenteException {
        servicoUsuarios.cadastrarUsuario(usuario);
    }

    @Override
    public void atualizarUsuario(Usuario usuario, String emailAtualizar) throws UsuarioInexistenteException {
         servicoUsuarios.atualizarUsuario(usuario, emailAtualizar);
    }

    @Override
    public void excluirUsuario(String email) throws UsuarioInexistenteException {
        servicoUsuarios.excluirUsuario(email);
    }

    @Override
    public List<ListarUsuario> listarUsuario() {
        return servicoUsuarios.listarUsuario();
    }

    @Override
    public Usuario BuscarUsuarioPorEmail(String email) throws UsuarioInexistenteException {
        return servicoUsuarios.BuscarUsuarioPorEmail(email);
    }

    @Override
    public List<ListarUsuario> BuscarUsuarioPorNome(String nome) throws UsuarioInexistenteException {
        return servicoUsuarios.BuscarUsuarioPorNome(nome);
    }

    @Override
    public void cadastrarAdocao(Adocao adocao, Animal animal, String email, String nomeClassificacao, String nomeRaca) throws UsuarioInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException {
        servicoAdocao.cadastrarAdocao(adocao, animal, email, nomeClassificacao, nomeRaca);
    }

    @Override
    public void excluirAdocao(Long codigo) throws AdocaoInexistenteException {
         servicoAdocao.excluirAdocao(codigo);
    }

    @Override
    public List<ListarAdocao> listarAdocao() {
        return servicoAdocao.listarAdocao();
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorAnimal(String nomeAnimal) {
        return servicoAdocao.buscarAdocaoPorAnimal(nomeAnimal);
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorClassificacao(String nomeClassificacao) {
        return servicoAdocao.buscarAdocaoPorClassificacao(nomeClassificacao);
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca) {
        return servicoAdocao.buscarAdocaoPorRaca(nomeRaca);
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorAnunciador(String nomeAnunciador){
        return servicoAdocao.buscarAdocaoPorAnunciador(nomeAnunciador);
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorAdotante(String nomeAdotante) {
        return servicoAdocao.buscarAdocaoPorAdotante(nomeAdotante);
    }

    @Override
    public List<ListarAdocao> buscarPorData(Date data) {
        return servicoAdocao.buscarPorData(data);
    }

    @Override
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException {
        return servicoAdocao.buscarAdocaoPorCodigo(codigo);
    }

    @Override
    public void interesseAdocao(String email,long codigo)throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoJaRealizadaException{
        servicoAdocao.interesseAdocao(email, codigo);
    }

    @Override
    public void escolherAdotante(Long codigo, String nomeAdotante) throws UsuarioInexistenteException {
        servicoAdocao.escolherAdotante(codigo, nomeAdotante);
    }

    @Override
    public void cadastrarAnimal(Animal animal, String classificacaoNome, String racaNome) throws ClassificacaoInexistenteException, RacaInexistenteException {
        servicoAnimal.cadastrarAnimal(animal, classificacaoNome, racaNome);
    }

    @Override
    public void atualizarAnimal(Animal animal, Long codigoAtualizado, String classificacaoNome, String racaNome) throws AnimalInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException {
        servicoAnimal.atualizarAnimal(animal, codigoAtualizado, classificacaoNome, racaNome);
    }

    @Override
    public void deletarAnimal(Long animal) throws AnimalInexistenteException {
        servicoAnimal.deletarAnimal(animal);
    }

    @Override
    public Animal consultarAnimalPorCodigo(Long codigo) throws AnimalInexistenteException {
        return servicoAnimal.consultarAnimalPorCodigo(codigo);
    }

    @Override
    public List<Animal> listarAnimal() {
        return servicoAnimal.listarAnimal();
    }

    @Override
    public List<Animal> buscarAnimalPorNome(String nome) {
        return servicoAnimal.buscarAnimalPorNome(nome);
    }

    @Override
    public List<Animal> buscarAnimalPorClassificacao(String nomeClassificacao) {
        return servicoAnimal.buscarAnimalPorClassificacao(nomeClassificacao);
    }

    @Override
    public List<Animal> buscarAnimalPorRaca(String nomeRaca) {
        return servicoAnimal.buscarAnimalPorRaca(nomeRaca);
    }
    
    @Override
    public void cadastrarClassificacao(Classificacao classificacao) throws ClassificacaoExistenteException {
        servicoClassificacao.cadastrarClassificacao(classificacao);
    }

    @Override
    public void atualizarClassificacao(String nomeAtual, String nomeAtualizar) throws ClassificacaoInexistenteException {
        servicoClassificacao.atualizarClassificacao(nomeAtual, nomeAtualizar);
    }

    @Override
    public List<Classificacao> listarClassificacao() {
        return servicoClassificacao.listarClassificacao();
    }

    @Override
    public Classificacao buscarClassificacaoPorNome(String nome) throws ClassificacaoInexistenteException {
        return servicoClassificacao.buscarClassificacaoPorNome(nome);
    }

    @Override
    public void cadastrarRaca(Raca raca, String classificacaoNome) throws RacaExistenteException, ClassificacaoInexistenteException {
        servicoRaca.cadastrarRaca(raca, classificacaoNome);
    }

    @Override
    public void atualizarRaca(String nomeAtual, String nomeAtualizar, String classificacaoNome) throws RacaInexistenteException, ClassificacaoInexistenteException {
        servicoRaca.atualizarRaca(nomeAtual, nomeAtualizar, classificacaoNome);
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
    
}
