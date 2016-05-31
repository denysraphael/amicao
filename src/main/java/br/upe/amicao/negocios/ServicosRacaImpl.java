/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.entidades.Classificacao;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.RacaExistenteException;
import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.persistencia.RepositorioRaca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicosRacaImpl implements ServicosRaca{

    @Autowired
    private RepositorioRaca repositorioRaca;
    @Autowired
    private ServicosClassificacao servicosClassificacao;
      
    @Override
    @Transactional(rollbackFor = RacaExistenteException.class)
    public void cadastrarRaca(Raca raca, String classificacaoNome) throws RacaExistenteException, ClassificacaoInexistenteException {
        if(repositorioRaca.findByNome(raca.getNome())!=null){
            throw new RacaExistenteException();
        }
        else{
            Classificacao classificacao = servicosClassificacao.buscarClassificacaoPorNome(classificacaoNome);
            classificacao.setCodigo(classificacao.getCodigo());
            raca.setClassificacao(classificacao);
            repositorioRaca.save(raca);
        }
    }

    @Override
    @Transactional(rollbackFor = RacaInexistenteException.class)
    public void atualizarRaca(String nomeAtual, String nomeAtualizar,String classificacaoNome) throws RacaInexistenteException, ClassificacaoInexistenteException {
        Raca racaAtualizar = repositorioRaca.findByNome(nomeAtual);
        if(racaAtualizar==null){
            throw new RacaInexistenteException();
        }
        racaAtualizar.setNome(nomeAtualizar);
        racaAtualizar.setClassificacao(servicosClassificacao.buscarClassificacaoPorNome(classificacaoNome));
        repositorioRaca.save(racaAtualizar);
    }

    @Override
    public List<Raca> listarRaca() {
        return (List<Raca>) repositorioRaca.findAll();
    }

    @Override
    public Raca buscarRacaPorNome(String nome) throws RacaInexistenteException {
        return repositorioRaca.findByNome(nome);
    }

    @Override
    public List<Raca> buscarRacaPorClassificacao(String classificacaoNome) {
        return(List<Raca>) repositorioRaca.buscarPorClassificacao(classificacaoNome);
    }
    
}
