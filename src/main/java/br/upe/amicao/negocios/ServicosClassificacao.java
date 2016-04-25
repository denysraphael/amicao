/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
import br.upe.amicao.exceptions.ClassificacaoExistenteException;
import br.upe.amicao.entidades.Classificacao;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author -Denys
 */
public interface ServicosClassificacao extends Serializable{
    public void cadastrarClassificacao(Classificacao classificacao) throws ClassificacaoExistenteException;
    public void atualizarClassificacao(String nomeAtual, String nomeAtualizar) throws ClassificacaoInexistenteException;
    public List<Classificacao> listarClassificacao();
    public Classificacao buscarClassificacaoPorNome(String nome) throws ClassificacaoInexistenteException;
}
