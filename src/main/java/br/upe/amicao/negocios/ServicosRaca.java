/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.RacaExistenteException;
import br.upe.amicao.entidades.Raca;
import java.io.Serializable;
import java.util.List;

public interface ServicosRaca extends Serializable {

    public void cadastrarRaca(Raca raca) throws RacaExistenteException;

    public void atualizarRaca(Raca raca) throws RacaInexistenteException;

    public List<Raca> listarRaca();

    public Raca buscarRacaPorNome(String nome) throws RacaInexistenteException;

    public List<Raca> buscarRacaPorClassificacao(String classificacaoNome);
}
