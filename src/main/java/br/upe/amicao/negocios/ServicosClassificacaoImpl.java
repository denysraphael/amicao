/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.entidades.Classificacao;
import br.upe.amicao.persistencia.RepositorioClassificacao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author -Denys
 */
@Service
public class ServicosClassificacaoImpl implements ServicosClassificacao{

    @Autowired
    private RepositorioClassificacao repositorioClassificacao;
     
    @Override
    public void cadastrarClassificacao(Classificacao classificacao) throws ClassificacaoExistenteException {
        if(repositorioClassificacao.findByNome(classificacao.getNome())!=null) 
            throw new ClassificacaoExistenteException();
        repositorioClassificacao.save(classificacao);
    }

    @Override
    public void atualizarClassificacao(String nomeAtual, String nomeAtualizar) throws ClassificacaoInexistenteException {
        Classificacao classificacaoAtualizar = repositorioClassificacao.findByNome(nomeAtual);
        if(classificacaoAtualizar==null){
            throw new ClassificacaoInexistenteException();
        }
        classificacaoAtualizar.setNome(nomeAtualizar);
        repositorioClassificacao.save(classificacaoAtualizar);
    }

    @Override
    public List<Classificacao> listarClassificacao() {
        return (List<Classificacao>) repositorioClassificacao.findAll();
    }

    @Override
    public Classificacao buscarClassificacaoPorNome(String nome) throws ClassificacaoInexistenteException {
        if(repositorioClassificacao.findByNome(nome)==null){
            throw new ClassificacaoInexistenteException();
        }
        return repositorioClassificacao.findByNome(nome);
    }
    
}
