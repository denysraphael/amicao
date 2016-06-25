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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class WSControllerUsuario {

    private static Logger log = Logger.getLogger(WSControllerUsuario.class);

    @Autowired
    private ServicosFachada fachada;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> cadastrarUsuario(Usuario u) {
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

    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public ModelAndView getData(
            @RequestParam String tipo,
            @RequestParam String dado) {
        List<String> list = getList(tipo, dado);

        ModelAndView model = new ModelAndView("buscaUsuario");
        model.addObject("lists", list);

        return model;
    }

    private List<String> getList(String tipo, String dado) {
        List<String> list = new ArrayList<String>();

        switch (tipo) {
            case "codigo":
                try {
                    List<Usuario> users = this.fachada.buscarUsuarioPorNome(dado);

                    for (Usuario u : users) {
                        String s = "Código do Usuário: " + u.getCodigo();;
                        s += ", Nome: " + u.getNome()
                                + ", E-mail: " + u.getEmail()
                                + ", Telefone: " + u.getTelefone()
                                + ", Status: " + u.isAtivo();

                        list.add(s);
                    }

                    return list;
                } catch (UsuarioInexistenteException ex) {
                    list.add("Sem Resultados");
                    return list;
                }
            case "email":
                try {
                    Usuario u = this.fachada.buscarUsuarioPorEmail(dado);

                    String s = "Código do Usuário: " + u.getCodigo();;
                    s += ", Nome: " + u.getNome()
                            + ", E-mail: " + u.getEmail()
                            + ", Telefone: " + u.getTelefone()
                            + ", Status: " + u.isAtivo();

                    list.add(s);

                    return list;
                } catch (UsuarioInexistenteException ex) {
                    list.add("Sem Resultados");
                    return list;
                }

            case "todos":
                List<Usuario> users = this.fachada.listarUsuario();

                if (users.isEmpty()) {
                    list.add("Sem Resultados");
                    return list;
                } else {
                    for (Usuario u : users) {
                        String s = "Código do Usuário: " + u.getCodigo();
                        s += ", Nome: " + u.getNome()
                                + ", E-mail: " + u.getEmail()
                                + ", Telefone: " + u.getTelefone()
                                + ", Status: " + u.isAtivo();

                        list.add(s);
                    }

                    return list;
                }
            default:
                list.add("Sem Resultados");
                return list;
        }
    }
    
    @RequestMapping(value = "/atualizar", method = RequestMethod.POST)
    public String atualizarUsuario(
            @RequestParam String emailAtual,
            @RequestParam String email,
            @RequestParam String nome,
            @RequestParam String senha,
            @RequestParam String telefone) {
        try {
            Usuario novoUsuario = this.fachada.buscarUsuarioPorEmail(emailAtual);
            
            if (!email.equals("")) {
                novoUsuario.setEmail(email);
            }
            
            if (!nome.equals("")) {
                novoUsuario.setNome(nome);
            }
            
            if (!senha.equals("")) {
                novoUsuario.setSenha(senha);
            }
            
            if (!telefone.equals("")) {
                novoUsuario.setTelefone(telefone);
            }
            
            this.fachada.atualizarUsuario(novoUsuario);
            
            return "buscaUsuario";
        } catch (UsuarioInexistenteException ex) {
            return "cadastroUsuario";
        }
    }
    
    @RequestMapping(value = "/desativar", method = RequestMethod.POST)
    public String desativarUsuario(
            @RequestParam String email,
            @RequestParam String senha) {
        try {
            Usuario u = this.fachada.buscarUsuarioPorEmailESenha(email, senha);
            
            this.fachada.desativarUsuario(u);
            return "buscaUsuario";
        } catch (UsuarioInexistenteException ex) {
            return "cadastroUsuario";
        }
    }

    @RequestMapping("/cadastro")
    public String formCadastrar() {
        return "cadastroUsuario";
    }

    @RequestMapping("/busca")
    public String formBuscar() {
        return "buscaUsuario";
    }
}
