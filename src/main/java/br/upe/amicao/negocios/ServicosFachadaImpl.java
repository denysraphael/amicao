/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Classificacao;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
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
    private ServicosUsuario servicosUsuarios;
    @Autowired
    private ServicosAdocao servicosAdocao;
    @Autowired
    private ServicosAnimal servicosAnimal;
    @Autowired
    private ServicosClassificacao servicosClassificacao;
    @Autowired
    private ServicosRaca servicosRaca;
    
    @Override
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException, UsuarioInexistenteException {
        servicosUsuarios.cadastrarUsuario(usuario);
    }

    @Override
    public void atualizarUsuario(Usuario usuario, String emailAtualizar) throws UsuarioInexistenteException {
         servicosUsuarios.atualizarUsuario(usuario, emailAtualizar);
    }

    @Override
    public void excluirUsuario(String email) throws UsuarioInexistenteException {
        servicosUsuarios.excluirUsuario(email);
    }

    @Override
    public List<ListarUsuario> listarUsuario() {
        return servicosUsuarios.listarUsuario();
    }

    @Override
    public Usuario BuscarUsuarioPorEmail(String email) throws UsuarioInexistenteException {
        return servicosUsuarios.BuscarUsuarioPorEmail(email);
    }

    @Override
    public List<ListarUsuario> BuscarUsuarioPorNome(String nome) throws UsuarioInexistenteException {
        return servicosUsuarios.BuscarUsuarioPorNome(nome);
    }

    @Override
    public void cadastrarAdocao(Adocao adocao, Animal animal, String email, String nomeClassificacao, String nomeRaca) throws UsuarioInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException {
        servicosAdocao.cadastrarAdocao(adocao, animal, email, nomeClassificacao, nomeRaca);
    }

    @Override
    public void excluirAdocao(Long codigo) throws AdocaoInexistenteException {
         servicosAdocao.excluirAdocao(codigo);
    }

    @Override
    public List<ListarAdocao> listarAdocao() {
        return servicosAdocao.listarAdocao();
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorAnimal(String nomeAnimal) {
        return servicosAdocao.buscarAdocaoPorAnimal(nomeAnimal);
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorClassificacao(String nomeClassificacao) {
        return servicosAdocao.buscarAdocaoPorClassificacao(nomeClassificacao);
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca) {
        return servicosAdocao.buscarAdocaoPorRaca(nomeRaca);
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorUsuarioAnunciador(String nomeUsuario) {
        return servicosAdocao.buscarAdocaoPorUsuarioAnunciador(nomeUsuario);
    }

    @Override
    public List<ListarAdocao> buscarPorUsuarioEscolhido(String nomeUsuario) {
        return servicosAdocao.buscarPorUsuarioEscolhido(nomeUsuario);
    }

    @Override
    public List<ListarAdocao> buscarPorData(Date data) {
        return servicosAdocao.buscarPorData(data);
    }

    @Override
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException {
        return servicosAdocao.buscarAdocaoPorCodigo(codigo);
    }

    @Override
    public void interesseAdocao(String email, long codigo) throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoExistenteException {
        servicosAdocao.interesseAdocao(email, codigo);
    }

    @Override
    public void realizarAdocao() throws UsuarioInexistenteException {
        servicosAdocao.realizarAdocao();
    }

    @Override
    public void cadastrarAnimal(Animal animal, String classificacaoNome, String racaNome) throws ClassificacaoInexistenteException, RacaInexistenteException {
        servicosAnimal.cadastrarAnimal(animal, classificacaoNome, racaNome);
    }

    @Override
    public void atualizarAnimal(Animal animal, Long codigoAtualizado, String classificacaoNome, String racaNome) throws AnimalInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException {
        servicosAnimal.atualizarAnimal(animal, codigoAtualizado, classificacaoNome, racaNome);
    }

    @Override
    public void deletarAnimal(Long animal) throws AnimalInexistenteException {
        servicosAnimal.deletarAnimal(animal);
    }

    @Override
    public Animal consultarAnimalPorCodigo(Long codigo) throws AnimalInexistenteException {
        return servicosAnimal.consultarAnimalPorCodigo(codigo);
    }

    @Override
    public List<Animal> listarAnimal() {
        return servicosAnimal.listarAnimal();
    }

    @Override
    public List<Animal> buscarAnimalPorNome(String nome) {
        return servicosAnimal.buscarAnimalPorNome(nome);
    }

    @Override
    public List<Animal> buscarAnimalPorClassificacao(String nomeClassificacao) {
        return servicosAnimal.buscarAnimalPorClassificacao(nomeClassificacao);
    }

    @Override
    public List<Animal> buscarAnimalPorRaca(String nomeRaca) {
        return servicosAnimal.buscarAnimalPorRaca(nomeRaca);
    }
    
    @Override
    public void cadastrarClassificacao(Classificacao classificacao) throws ClassificacaoExistenteException {
        servicosClassificacao.cadastrarClassificacao(classificacao);
    }

    @Override
    public void atualizarClassificacao(String nomeAtual, String nomeAtualizar) throws ClassificacaoInexistenteException {
        servicosClassificacao.atualizarClassificacao(nomeAtual, nomeAtualizar);
    }

    @Override
    public List<Classificacao> listarClassificacao() {
        return servicosClassificacao.listarClassificacao();
    }

    @Override
    public Classificacao buscarClassificacaoPorNome(String nome) throws ClassificacaoInexistenteException {
        return servicosClassificacao.buscarClassificacaoPorNome(nome);
    }

    @Override
    public void cadastrarRaca(Raca raca, String classificacaoNome) throws RacaExistenteException, ClassificacaoInexistenteException {
        servicosRaca.cadastrarRaca(raca, classificacaoNome);
    }

    @Override
    public void atualizarRaca(String nomeAtual, String nomeAtualizar, String classificacaoNome) throws RacaInexistenteException, ClassificacaoInexistenteException {
        servicosRaca.atualizarRaca(nomeAtual, nomeAtualizar, classificacaoNome);
    }

    @Override
    public List<Raca> listarRaca() {
        return servicosRaca.listarRaca();
    }

    @Override
    public Raca buscarRacaPorNome(String nome) throws RacaInexistenteException {
        return servicosRaca.buscarRacaPorNome(nome);
    }

    @Override
    public List<Raca> buscarRacaPorClassificacao(String classificacaoNome) {
        return servicosRaca.buscarRacaPorClassificacao(classificacaoNome);
    }
    
}
