/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.entidades.Raca;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author -Denys
 */
public interface ServicosRaca extends Serializable{
    public void cadastrarRaca(Raca raca, String classificacaoNome) throws RacaExistenteException, ClassificacaoInexistenteException;
    public void atualizarRaca(String nomeAtual, String nomeAtualizar, String classificacaoNome) throws RacaInexistenteException, ClassificacaoInexistenteException;
    public List<Raca> listarRaca();
    public Raca buscarRacaPorNome(String nome) throws RacaInexistenteException;
    public List<Raca> buscarRacaPorClassificacao(String classificacaoNome);
}
