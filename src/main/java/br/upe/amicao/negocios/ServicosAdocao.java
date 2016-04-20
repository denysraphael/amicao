/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 * @author -Denys
 */
public interface ServicosAdocao extends Serializable {
    public void cadastrarAdocao(Adocao adocao, Animal animal, String email, String nomeClassificacao, String nomeRaca) throws UsuarioInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException;
    public void excluirAdocao(Long codigo) throws AdocaoInexistenteException;
    public List<ListarAdocao> listarAdocao();
    public List<ListarAdocao> buscarAdocaoPorAnimal(String nomeAnimal);
    public List<ListarAdocao> buscarAdocaoPorClassificacao(String nomeClassificacao);
    public List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca);
    public List<ListarAdocao> buscarAdocaoPorUsuarioAnunciador(String nomeUsuario);
    public List<ListarAdocao> buscarPorUsuarioEscolhido(String nomeUsuario);
    public List<ListarAdocao> buscarPorData(Date data);
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException;
    public void interesseAdocao(String email,long codigo)throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoExistenteException;
    public void realizarAdocao() throws UsuarioInexistenteException;
}
