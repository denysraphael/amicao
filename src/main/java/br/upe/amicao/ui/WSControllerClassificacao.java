/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.ui;

import br.upe.amicao.entidades.Classificacao;
import br.upe.amicao.exceptions.ClassificacaoExistenteException;
import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
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
@RequestMapping("/amicao/classificacao")
public class WSControllerClassificacao {
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WSControllerClassificacao.class);
    @Autowired
    private ServicosFachada fachada;
    
    @RequestMapping(value = "/listar", headers="Accept=*/*", method = RequestMethod.GET)
    public @ResponseBody List<Classificacao> listarClassificacao(){
        return fachada.listarClassificacao();
    }
    
    @RequestMapping(value = "/cadastrar", headers="Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> adicionarClassificacao(Classificacao classificacao) {
        try{
            fachada.cadastrarClassificacao(classificacao);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(ClassificacaoExistenteException e){
            return new ResponseEntity<ClassificacaoExistenteException>(e, HttpStatus.BAD_REQUEST);
        }    
    }
    
    @RequestMapping(value = "/buscar")
    public @ResponseBody Classificacao buscarClassificacao(String nome) {
        try {
            return fachada.buscarClassificacaoPorNome(nome);
        } catch (ClassificacaoInexistenteException ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/atualizar", headers="Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> atualizarClassificacao(String nomeAtual, String nomeAtualizar) {
        Classificacao classificacao;
        try {
            classificacao = fachada.buscarClassificacaoPorNome(nomeAtual);
            classificacao.setNome(nomeAtualizar);
            fachada.atualizarClassificacao(nomeAtual,nomeAtualizar);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (ClassificacaoInexistenteException ex) {
            return new ResponseEntity<ClassificacaoInexistenteException>(ex, HttpStatus.BAD_REQUEST);
        }
    }
}
