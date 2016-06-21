/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.ui;

import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.exceptions.AdocaoJaRealizadaException;
import br.upe.amicao.exceptions.ProprioUsuarioAnunciadorException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
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

@Controller
@RequestMapping("adocao")
public class WSControllerAdocao {
    
    /*static Logger log = Logger.getLogger(WSControllerAdocao.class);
    @Autowired
    private ServicosFachada fachada;
    
    @RequestMapping(value="/cadastrar",produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> cadastrarAdocao(Adocao adocao, Long codigoAnimal,String emailUsuario, String nomeClassificacao, String nomeRaca) {
        try {
            fachada.cadastrarAdocao(adocao, codigoAnimal, nomeRaca);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UsuarioInexistenteException e) {
            return new ResponseEntity<UsuarioInexistenteException>(e,HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/listar")
    public @ResponseBody List<ListarAdocao> listarAdocao(){
        return fachada.listarAdocao();
    }
    
    @RequestMapping(value="/remover",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> excluirAdocao(Long codigo){ 
        try{
            fachada.excluirAdocao(codigo);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(AdocaoInexistenteException e){
            return new ResponseEntity<AdocaoInexistenteException>(e,HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value="/interesseAdocao",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> interesseAdocao(String email,long codigo){
        
        try{
            fachada.interesseAdocao(email, codigo);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(UsuarioInexistenteException e){
           return new ResponseEntity<UsuarioInexistenteException>(e,HttpStatus.BAD_REQUEST);
        } catch (ProprioUsuarioAnunciadorException ex) {
            return new ResponseEntity<ProprioUsuarioAnunciadorException>(ex,HttpStatus.BAD_REQUEST);
        } catch (AdocaoJaRealizadaException ex) {
            return new ResponseEntity<AdocaoJaRealizadaException>(ex,HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/buscarPorAnimal")
    public @ResponseBody List<ListarAdocao> buscarAdocaoPorAnimal(String nomeAnimal) {
        return fachada.buscarAdocaoPorAnimal(nomeAnimal);
    }
    
    @RequestMapping(value = "/buscarPorClassificacao")
    public @ResponseBody List<ListarAdocao> buscarAdocaoPorClassificacao(String nomeClassificacao) {
        return fachada.buscarAdocaoPorClassificacao(nomeClassificacao);
    }
    
    @RequestMapping(value = "/buscarAdocaoPorRaca")
    public @ResponseBody List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca) {
        return fachada.buscarAdocaoPorRaca(nomeRaca);
    }
    
    @RequestMapping(value = "/buscarPorAnunciador")
    public @ResponseBody List<ListarAdocao> buscarAdocaoPorAnunciador(String nomeAnunciador) {
        return fachada.buscarAdocaoPorAnunciador(nomeAnunciador);
    }
    
    
    @RequestMapping(value = "/buscarPorAdotante")
    public @ResponseBody List<ListarAdocao> buscarAdocaoPorAdotante(String nomeAdotante) {
        return fachada.buscarAdocaoPorAdotante(nomeAdotante);
    }
    
    @RequestMapping(value = "/buscarPorData")
    public @ResponseBody List<ListarAdocao> buscarAdocaoPorData(Date data) {
        return fachada.buscarPorData(data);
    }*/
}
