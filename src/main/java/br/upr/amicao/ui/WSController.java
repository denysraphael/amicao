/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upr.amicao.ui;

import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.listar.ListarUsuario;
import br.upe.amicao.negocios.ServicosFachada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author -Denys
 */
@RestController
@RequestMapping("/amicao")
public class WSController {
    
    @Autowired
    private ServicosFachada fachada;
    
     @RequestMapping(value = "/cadastrar", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<ListarUsuario> listarAluno() {
        return this.fachada.listarUsuario();
    }
    
    @RequestMapping(value = "/usuario/listar")
    public ResponseEntity<?> cadastrarUsuario(Usuario u){
        try{
            this.fachada.cadastrarUsuario(u);
        }catch (Exception e){
            return new ResponseEntity<Exception> (e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
