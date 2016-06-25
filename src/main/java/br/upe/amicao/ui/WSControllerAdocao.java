/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.ui;

import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AdocaoExistenteException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.exceptions.AdocaoJaRealizadaException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.exceptions.ProprioUsuarioAnunciadorException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.negocios.ServicosFachada;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("adocao")
public class WSControllerAdocao {

    static Logger log = Logger.getLogger(WSControllerAdocao.class);
    @Autowired
    private ServicosFachada fachada;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String cadastrarAdocao(
            @RequestParam String descricao,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String nomeAnimal) {

        try {
            Usuario u = this.fachada.buscarUsuarioPorEmailESenha(email, senha);
            Animal a = this.fachada.buscarAnimalPorDono(u, nomeAnimal);

            Adocao adocao = new Adocao(descricao, a, u);

            this.fachada.cadastrarAdocao(adocao);
        } catch (UsuarioInexistenteException e) {
            return "cadastroUsuario";
        } catch (AnimalInexistenteException ex) {
            return "cadastroAnimal";
        } catch (AdocaoExistenteException ex) {
            java.util.logging.Logger.getLogger(WSControllerAdocao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "criarAdocao";
    }

    @RequestMapping(value = "realizarAdocao", method = RequestMethod.POST)
    public String realizarAdocao(
            @RequestParam String codAdocao,
            @RequestParam String email,
            @RequestParam String senha) {
        try {
            Adocao a = this.fachada.buscarAdocaoPorCodigo(Long.valueOf(codAdocao));
            Usuario u = this.fachada.buscarUsuarioPorEmailESenha(email, senha);

            a.setAdotante(u);
            this.fachada.desativarAdocao(a);
        } catch (AdocaoInexistenteException ex) {
            return "Código Incorreto!";
        } catch (UsuarioInexistenteException ex) {
            return "cadastroUsuario";
        }

        return "buscarAdocao";
    }

    @RequestMapping(value = "buscar", method = RequestMethod.GET)
    public ModelAndView getData(
            @RequestParam String tipo,
            @RequestParam String descricao) {
        List<String> list = getList(tipo, descricao);

        ModelAndView model = new ModelAndView("buscarAdocao");
        model.addObject("lists", list);

        return model;
    }

    private List<String> getList(String tipo, String dado) {
        List<String> list = new ArrayList<String>();

        switch (tipo) {
            case "descricao":
                try {
                    List<Adocao> adocoes = this.fachada.buscarAdocaoPorDescricao(dado);

                    for (Adocao a : adocoes) {
                        String s = "Código da Adoção: " + a.getCodigo();
                        s += ", Animal: " + a.getAnimal().getNome()
                                + ", Descrição: " + a.getDescricao()
                                + ", Doador: " + a.getAnunciador().getNome()
                                + ", Status: " + a.isAtivo();

                        list.add(s);
                    }

                    return list;
                } catch (AdocaoInexistenteException ex) {
                    list.add("Sem Resultados");
                    return list;
                }
            case "nomeAnimal":
                try {
                    List<Adocao> adocoes = this.fachada.buscarAdocaoPorNomeAnimal(dado);

                    for (Adocao a : adocoes) {
                        String s = "Código da Adoção: " + a.getCodigo();
                        s += ", Animal: " + a.getAnimal().getNome()
                                + ", Descrição: " + a.getDescricao()
                                + ", Doador: " + a.getAnunciador().getNome()
                                + ", Status: " + a.isAtivo();

                        list.add(s);
                    }

                    return list;
                } catch (AdocaoInexistenteException ex) {
                    list.add("Sem Resultados");
                    return list;
                }
            case "raca":
                try {
                    List<Adocao> adocoes = this.fachada.buscarAdocaoPorRaca(dado);

                    for (Adocao a : adocoes) {
                        String s = "Código da Adoção: " + a.getCodigo();
                        s += ", Animal: " + a.getAnimal().getNome()
                                + ", Descrição: " + a.getDescricao()
                                + ", Doador: " + a.getAnunciador().getNome()
                                + ", Status: " + a.isAtivo();

                        list.add(s);
                    }

                    return list;
                } catch (AdocaoInexistenteException ex) {
                    list.add("Sem Resultados");
                    return list;
                }
            case "todos":
                List<Adocao> adocoes = this.fachada.listarAdocoes();

                if (adocoes.isEmpty()) {
                    list.add("Sem Resultados");
                    return list;
                } else {
                    for (Adocao a : adocoes) {
                        String s = "Código da Adoção: " + a.getCodigo();
                        s += ", Animal: " + a.getAnimal().getNome()
                                + ", Descrição: " + a.getDescricao()
                                + ", Doador: " + a.getAnunciador().getNome()
                                + ", Status: " + a.isAtivo();

                        list.add(s);
                    }

                    return list;
                }
            default:
                list.add("Sem Resultados");
                return list;
        }
    }

    @RequestMapping("/cadastro")
    public String formCriar() {
        return "criarAdocao";
    }

    @RequestMapping("/busca")
    public String formBuscar() {
        return "buscarAdocao";
    }
}
