/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upr.amicao.ui;

import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.exceptions.AdocaoExistenteException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
import br.upe.amicao.exceptions.ProprioUsuarioAnunciadorException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.listar.ListarAdocao;
import br.upe.amicao.negocios.ServicosFachada;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author -Denys
 */
@Controller
@RequestMapping("/amicao/adocao")
public class WSControllerAdocao {
    
    static Logger log = Logger.getLogger(WSControllerAdocao.class);
    @Autowired
    private ServicosFachada fachada;
    
    @RequestMapping(value="/cadastrar",produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> cadastrarAdocao(Adocao adocao,Animal animal,String emailUsuario,String nomeClassificacao, String nomeRaca) {
        try {
            fachada.cadastrarAdocao(adocao, animal, nomeRaca, nomeClassificacao, nomeRaca);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UsuarioInexistenteException e) {
            return new ResponseEntity<UsuarioInexistenteException>(e,HttpStatus.BAD_REQUEST);
        } catch (ClassificacaoInexistenteException ex) {
            return new ResponseEntity<ClassificacaoInexistenteException>(ex,HttpStatus.BAD_REQUEST);
        } catch (RacaInexistenteException ex) {
            return new ResponseEntity<RacaInexistenteException>(ex,HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/listar")
    public @ResponseBody List<ListarAdocao> listarAdocao(){
        return fachada.listarAdocao();
    }
    
    @RequestMapping(value="/remover",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> excluirAdocao(Long id){
        
        try{
            fachada.excluirAdocao(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(AdocaoInexistenteException e){
            return new ResponseEntity<AdocaoInexistenteException>(e,HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value="/interesseAdocao",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> interesseAdocao(String email,long id){
        
        try{
            fachada.interesseAdocao(email, id);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(UsuarioInexistenteException e){
           return new ResponseEntity<UsuarioInexistenteException>(e,HttpStatus.BAD_REQUEST);
        } catch (ProprioUsuarioAnunciadorException ex) {
            return new ResponseEntity<ProprioUsuarioAnunciadorException>(ex,HttpStatus.BAD_REQUEST);
        } catch (AdocaoExistenteException ex) {
            return new ResponseEntity<AdocaoExistenteException>(ex,HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/buscarPorAnimal")
    public @ResponseBody List<ListarAdocao> buscarPorAnimal(String nomeAnimal) {
        return fachada.buscarAdocaoPorAnimal(nomeAnimal);
    }
    
    @RequestMapping(value = "/buscarPorClassificacao")
    public @ResponseBody List<ListarAdocao> buscarPorClassificacao(String nomeClassificacao) {
        return fachada.buscarAdocaoPorClassificacao(nomeClassificacao);
    }
    
    @RequestMapping(value = "/buscarPorRaca")
    public @ResponseBody List<ListarAdocao> buscarPorRaca(String nomeRaca) {
        return fachada.buscarAdocaoPorRaca(nomeRaca);
    }
    
    @RequestMapping(value = "/buscarPorUsuarioAnunciador")
    public @ResponseBody List<ListarAdocao> buscarPorUsuarioAnunciador(String nomeUsuarioAnunciador) {
        return fachada.buscarAdocaoPorUsuarioAnunciador(nomeUsuarioAnunciador);
    }
    
    
    @RequestMapping(value = "/buscarPorUsuarioEscolhido")
    public @ResponseBody List<ListarAdocao> buscarPorUsuarioEmprestado(String nomeUsuarioEscolhido) {
        return fachada.buscarPorUsuarioEscolhido(nomeUsuarioEscolhido);
    }
    
    @RequestMapping(value = "/buscarPorData")
    public @ResponseBody List<ListarAdocao> buscarPorData(Date data) {
        return fachada.buscarPorData(data);
    }
}
