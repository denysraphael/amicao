/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.ui;

import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.exceptions.AnimalExistenteException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.negocios.ServicosFachada;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/animal")
public class WSControllerAnimal {
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WSControllerAnimal.class);
    @Autowired
    private ServicosFachada fachada;
    
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public @ResponseBody List<Animal> listarAnimal(){
        return fachada.listarAnimal();
    }
    
//    @RequestMapping(value = "/buscarPorNome")
//    public @ResponseBody List<Animal> buscarPorNome(String nome) {
//        return fachada.buscarAnimalPorNome(nome);
//    }
//    
//    
//    @RequestMapping(value = "/buscarPorClassificacao")
//    public @ResponseBody List<Animal> buscarPorClassificacao(String nomeClassificacao) {
//        return fachada.buscarAnimalPorClassificacao(nomeClassificacao);
//    }
    
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> cadastrarAnimal(Animal a) {
        try {
            if (a.getNome() != null) {
                this.fachada.cadastrarAnimal(a);
            } else {
                return new ResponseEntity<String>("CAMPOS EM BRANCO", HttpStatus.BAD_REQUEST);
            }
        } catch (AnimalExistenteException e) {
            return new ResponseEntity<AnimalExistenteException>(e, HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<String>("Animal: " + a.getNome() + " Cadastrado com Sucesso", HttpStatus.OK);
    }
    
//    @RequestMapping(value = "/atualizar", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody ResponseEntity<?> atualizarAnimal(Animal animal, Long codigoAtualizado, String nomeClassificacao, String nomeRaca) {
//        try{
//            fachada.atualizarAnimal(animal, codigoAtualizado, nomeClassificacao, nomeRaca);
//            return new ResponseEntity<String>(HttpStatus.OK);
//        } catch (ClassificacaoInexistenteException ex) {
//            return new ResponseEntity<ClassificacaoInexistenteException>(ex, HttpStatus.BAD_REQUEST);
//        } catch (RacaInexistenteException ex) {
//            return new ResponseEntity<RacaInexistenteException>(ex, HttpStatus.BAD_REQUEST);
//        } catch (AnimalInexistenteException ex) {
//            return new ResponseEntity<AnimalInexistenteException>(ex, HttpStatus.BAD_REQUEST);
//        }
//    }
//    
//    @RequestMapping(value = "/excluir", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody ResponseEntity<?> excluirAnimal(Long codigo) {
//        try{
//            fachada.deletarAnimal(codigo);
//            return new ResponseEntity<String>(HttpStatus.OK);
//        } catch (AnimalInexistenteException ex) {
//            return new ResponseEntity<AnimalInexistenteException>(ex, HttpStatus.BAD_REQUEST);
//        } 
//    }
    
     
    @RequestMapping("/cadastro")
    public String formCadastrar() {
        return "cadastroAnimal";
    }
    
//    @RequestMapping("/atualiza")
//    public String formAtualizar() {
//        return "atualizaAnimal";
//    }
//    
//    @RequestMapping("/exclui")
//    public String formExcluir() {
//        return "excluirAnimal";
//    }
    
}
