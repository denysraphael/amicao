/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.ui;

import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AnimalExistenteException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.negocios.ServicosFachada;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/animal")
public class WSControllerAnimal {

    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WSControllerAnimal.class);
    @Autowired
    private ServicosFachada fachada;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public @ResponseBody
    List<Animal> listarAnimal() {
        return fachada.listarAnimal();
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String cadastrarAnimal(
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String nome,
            @RequestParam String racaAnimal,
            @RequestParam String classifAnimal,
            @RequestParam String caracteristicas,
            @RequestParam String dataNascimento) {

        try {
            // Buscando Usuário
            Usuario u = this.fachada.buscarUsuarioPorEmailESenha(email, senha);

            Animal a = new Animal();
            a.setDono(u);
            a.setNome(nome);
            a.setCaracteristicas(caracteristicas);

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

            a.setDataNascimento(f.parse(dataNascimento));

            this.fachada.cadastrarAnimal(a, racaAnimal, classifAnimal);
        } catch (ParseException ex) {
            Logger.getLogger(WSControllerAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AnimalExistenteException ex) {
            return "teste";
        } catch (UsuarioInexistenteException ex) {
            return "cadastroUsuario";
        }

        return "cadastroAnimal";
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public ModelAndView getData(
            @RequestParam String tipo,
            @RequestParam String dado) {
        List<String> list = getList(tipo, dado);

        ModelAndView model = new ModelAndView("buscarAnimal");
        model.addObject("lists", list);

        return model;
    }

    private List<String> getList(String tipo, String dado) {
        List<String> list = new ArrayList<String>();

        switch (tipo) {
            case "nome":
                try {
                    List<Animal> animais = this.fachada.buscarAnimalPorNome(dado);

                    for (Animal a : animais) {
                        String s = "Código do Animal: " + a.getCodigo();
                        s += ", Nome do Animal: " + a.getNome()
                                + ", Nome do Dono: " + a.getDono().getNome()
                                + ", Raça do Animal: " + a.getRaca().getNome();

                        list.add(s);
                    }

                    return list;
                } catch (AnimalInexistenteException e) {
                    list.add("Sem Resultados");
                    return list;
                }
            case "raca":
                try {
                    Raca raca = this.fachada.buscarRacaPorNome(dado);
                    List<Animal> animais = this.fachada.buscarAnimalPorRaca(raca);

                    for (Animal a : animais) {
                        String s = "Código do Animal: " + a.getCodigo();
                        s += ", Nome do Animal: " + a.getNome()
                                + ", Nome do Dono: " + a.getDono().getNome()
                                + ", Raça do Animal: " + a.getRaca().getNome();

                        list.add(s);
                    }

                    return list;
                } catch (AnimalInexistenteException | RacaInexistenteException e) {
                    list.add("Sem Resultados");
                    return list;
                }
            case "caracteristicas":
                try {
                    List<Animal> animais = this.fachada.buscarAnimalPorCaracteristicas(dado);

                    for (Animal a : animais) {
                        String s = "Código do Animal: " + a.getCodigo();
                        s += ", Nome do Animal: " + a.getNome()
                                + ", Nome do Dono: " + a.getDono().getNome()
                                + ", Raça do Animal: " + a.getRaca().getNome();

                        list.add(s);
                    }

                    return list;
                } catch (AnimalInexistenteException e) {
                    list.add("Sem Resultados");
                    return list;
                }
            case "todos":
                List<Animal> animais = this.fachada.listarAnimal();

                if (animais.isEmpty()) {
                    list.add("Sem Resultados");
                    return list;
                }

                for (Animal a : animais) {
                    String s = "Código do Animal: " + a.getCodigo();
                    s += ", Nome do Animal: " + a.getNome()
                            + ", Nome do Dono: " + a.getDono().getNome()
                            + ", Raça do Animal: " + a.getRaca().getNome();

                    list.add(s);
                }

                return list;

            default:
                list.add("Sem Resultados");
                return list;
        }
    }

    @RequestMapping(value = "/atualizar", method = RequestMethod.POST)
    public String atualizarUsuario(
            @RequestParam String codigo,
            @RequestParam String nome,
            @RequestParam String raca,
            @RequestParam String caracteristicas) {
        
        try {
            Animal a = this.fachada.buscarAnimalPorCodigo(Long.valueOf(codigo));
            
            if (!nome.equals("")) {
                a.setNome(nome);
            }
            if (!raca.equals("")) {
                Raca r = this.fachada.buscarRacaPorNome(raca);
                a.setRaca(r);
            }
            if (!caracteristicas.equals("")) {
                a.setCaracteristicas(caracteristicas);
            }
            
            this.fachada.atualizarAnimal(a);
            return "buscarAnimal";
        } catch (AnimalInexistenteException | RacaInexistenteException ex) {
            return "cadastroAnimal";
        }
    }
    
    @RequestMapping("/cadastro")
    public String formCadastrar() {
        return "cadastroAnimal";
    }

    @RequestMapping("/busca")
    public String formBuscar() {
        return "buscarAnimal";
    }
}
