/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.AdocaoJaRealizadaException;
import br.upe.amicao.listar.ListarAdocao;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.ProprioUsuarioAnunciadorException;
import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
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
    public List<ListarAdocao> buscarAdocaoPorAnunciador(String nomeAnunciador);
    public List<ListarAdocao> buscarAdocaoPorAdotante(String nomeAdotante);
    public List<ListarAdocao> buscarPorData(Date data);
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException;
    public void interesseAdocao(String email,long codigo)throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoJaRealizadaException;
    public void escolherAdotante() throws UsuarioInexistenteException;
}
