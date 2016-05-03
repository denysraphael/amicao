/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.ui;

import br.upe.amicao.entidades.Raca;
import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
import br.upe.amicao.exceptions.RacaExistenteException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.negocios.ServicosFachada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author -Denys
 */
@Controller
@RequestMapping("/raca")
public class WSControllerRaca {
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WSControllerRaca.class);
    @Autowired
    private ServicosFachada fachada;
    
    @RequestMapping(value = "/listar", headers="Accept=*/*", method = RequestMethod.GET)
    public @ResponseBody List<Raca> listarRaca(){
        return fachada.listarRaca();
    }
    
    @RequestMapping(value = "/cadastrar", headers="Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> adicionarRaca(Raca raca, String classificacaoNome) {
        try{
            fachada.cadastrarRaca(raca, classificacaoNome);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(ClassificacaoInexistenteException e){
            return new ResponseEntity<ClassificacaoInexistenteException>(e, HttpStatus.BAD_REQUEST);
        } catch(RacaExistenteException e){
            return new ResponseEntity<RacaExistenteException>(e, HttpStatus.BAD_REQUEST);
        }   
    }
    
    @RequestMapping(value = "/buscar")
    public @ResponseBody Raca buscarRaca(String nome) {
        try {
            return fachada.buscarRacaPorNome(nome);
        } catch (RacaInexistenteException ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/atualizar", headers="Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> atualizarRaca(String nomeAtual, String nomeAtualizar, String classificacaoNome) {
        Raca raca;
        try {
            raca = fachada.buscarRacaPorNome(nomeAtual);
            raca.setNome(nomeAtualizar);
            fachada.atualizarRaca(nomeAtual, nomeAtualizar, classificacaoNome);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (ClassificacaoInexistenteException ex) {
            return new ResponseEntity<ClassificacaoInexistenteException>(ex, HttpStatus.BAD_REQUEST);
        } catch (RacaInexistenteException ex) {
            return new ResponseEntity<RacaInexistenteException>(ex, HttpStatus.BAD_REQUEST);
        }
    }
}
