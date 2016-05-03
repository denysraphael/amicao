/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.ui;

import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.negocios.ServicosFachada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.upe.amicao.listar.ListarUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
/**
 *
 * @author -Denys
 */
@RestController
@RequestMapping("/amicao/usuario")
public class WSControllerUsuario {
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WSControllerUsuario.class);
    @Autowired
    private ServicosFachada fachada;
    
    @RequestMapping(value = "/listar", headers="Accept=*/*", method = RequestMethod.GET)
    public @ResponseBody List<ListarUsuario> listarUsuario(){
        return fachada.listarUsuario();
    }
    
    @RequestMapping(value = "/consultar")
    public @ResponseBody List<ListarUsuario> buscar(String nome){
        try{
            return fachada.BuscarUsuarioPorNome(nome);
        } catch (UsuarioInexistenteException ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/cadastrar", headers="Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> adicionarUsuario(Usuario usuario) {
        try {
            fachada.cadastrarUsuario(usuario);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UsuarioExistenteException ex) {
            return new ResponseEntity<UsuarioExistenteException>(ex, HttpStatus.BAD_REQUEST);
        } catch (UsuarioInexistenteException ex1) {
            return new ResponseEntity<UsuarioInexistenteException>(ex1, HttpStatus.BAD_REQUEST);
        }
    }
  
    @RequestMapping(value = "/atualizar", headers="Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> atualizarUsuario(Usuario usuario, String emailAtualizar) {
        try {
            fachada.atualizarUsuario(usuario, emailAtualizar);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UsuarioInexistenteException ex) {
            return new ResponseEntity<UsuarioInexistenteException>(ex, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/excluir", headers="Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> excluirUsuario(String email) {
        try {
            fachada.excluirUsuario(email);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UsuarioInexistenteException ex) {
            return new ResponseEntity<UsuarioInexistenteException>(ex, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/cadastro")
    public String formCadastrar() {
        return "UsuarioCadastrar";
    }
    
    @RequestMapping("/atualiza")
    public String formAtualizar() {
        return "UsuarioAtualizar";
    }
}
