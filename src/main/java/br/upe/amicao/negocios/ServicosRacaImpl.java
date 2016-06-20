/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.RacaExistenteException;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.persistencia.RepositorioRaca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicosRacaImpl implements ServicosRaca{

    @Autowired
    private RepositorioRaca repRaca;
      
    @Override
    @Transactional(rollbackFor = RacaExistenteException.class)
    public void cadastrarRaca(Raca raca) throws RacaExistenteException {
        try {
            Raca r = this.buscarRacaPorNome(raca.getNome());
            if (r != null) {
                throw new RacaExistenteException();
            }
        } catch (RacaInexistenteException e) {
            repRaca.save(raca);
        }
    }

    @Override
    @Transactional(rollbackFor = RacaInexistenteException.class)
    public void atualizarRaca(Raca raca) throws RacaInexistenteException {
        Raca racaAtualizar = this.buscarRacaPorNome(raca.getNome());
        
        if (racaAtualizar != null) {
            racaAtualizar.setAnimais(raca.getAnimais());
            racaAtualizar.setClassificacao(raca.getClassificacao());
            racaAtualizar.setNome(raca.getNome());
            
            repRaca.save(racaAtualizar);
        }
    }

    @Override
    public List<Raca> listarRaca() {
        return (List<Raca>) repRaca.findAll();
    }

    @Override
    public Raca buscarRacaPorNome(String nome) throws RacaInexistenteException {
        Raca r = repRaca.findByNome(nome);
        
        if (r == null) {
            throw new RacaInexistenteException();
        }
        
        return r;
    }

    @Override
    public List<Raca> buscarRacaPorClassificacao(String classificacaoNome) {
        return(List<Raca>) repRaca.findByClassificacao(classificacaoNome);
    }
    
}
