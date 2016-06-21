/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.ui;

import org.apache.log4j.Logger;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.negocios.ServicosFachada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
//@RequestMapping("/usuario")
public class WSControllerUsuario {

    private static Logger log = Logger.getLogger(WSControllerUsuario.class);
    
    @Autowired
    private ServicosFachada fachada;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public @ResponseBody
    List<Usuario> listarUsuario() {
        return fachada.listarUsuario();
    }

    @RequestMapping(value = "/buscar")
    public @ResponseBody
    List<Usuario> buscarUsuario(String nome) {
        try {
            return fachada.buscarUsuarioPorNome(nome);
        } catch (UsuarioInexistenteException ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> cadastrarUsuario(Usuario u) {
        try {
            if (u.getNome() != null) {
                this.fachada.cadastrarUsuario(u);
            } else {
                return new ResponseEntity<String>("CAMPOS EM BRANCO", HttpStatus.BAD_REQUEST);
            }
        } catch (UsuarioExistenteException e) {
            return new ResponseEntity<UsuarioExistenteException>(e, HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<String>("Usuário: " + u.getNome() + " Cadastrado com Sucesso", HttpStatus.OK);
    }

    /*@RequestMapping(value = "/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<?> cadastrarUsuario(Usuario usuario) {
        try {
            fachada.cadastrarUsuario(usuario);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UsuarioExistenteException ex) {
            return new ResponseEntity<UsuarioExistenteException>(ex, HttpStatus.BAD_REQUEST);
        } catch (UsuarioInexistenteException ex1) {
            return new ResponseEntity<UsuarioInexistenteException>(ex1, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/atualizar", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> atualizarUsuario(Usuario usuario, String emailAtualizar) {
        try {
            fachada.atualizarUsuario(usuario, emailAtualizar);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UsuarioInexistenteException ex) {
            return new ResponseEntity<UsuarioInexistenteException>(ex, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/excluir", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> excluirUsuario(String email) {
        try {
            fachada.excluirUsuario(email);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UsuarioInexistenteException ex) {
            return new ResponseEntity<UsuarioInexistenteException>(ex, HttpStatus.BAD_REQUEST);
        }
    }*/
    
    @RequestMapping("/cadastro")
    public String formCadastrar() {
        return "cadastroUsuario";
    }

    @RequestMapping("/atualiza")
    public String formAtualizar() {
        return "atualizaUsuario";
    }

    @RequestMapping("/exclui")
    public String formExcluir() {
        return "excluiUsuario";
    }

    @RequestMapping("/busca")
    public String formBuscar() {
        return "buscaUsuario";
    }

}
