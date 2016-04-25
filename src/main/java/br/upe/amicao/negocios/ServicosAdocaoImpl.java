/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.listar.ListarAdocao;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.exceptions.RacaInexistenteException;
import br.upe.amicao.exceptions.ProprioUsuarioAnunciadorException;
import br.upe.amicao.exceptions.ClassificacaoInexistenteException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.exceptions.AdocaoExistenteException;
import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.persistencia.RepositorioAdocao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author -Denys
 */
@Service
public class ServicosAdocaoImpl implements ServicosAdocao{

    @Autowired
    private RepositorioAdocao repositorioAdocao;
    
    @Autowired
    private ServicosUsuario servicosUsuario; 
       
    @Autowired
    private ServicosAnimal servicosAnimal;
    
    @Override
    public void cadastrarAdocao(Adocao adocao, Animal animal, String email, String nomeClassificacao, String nomeRaca) throws UsuarioInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException {
        Usuario usuario = servicosUsuario.BuscarUsuarioPorEmail(email);
        List<Adocao> listaAdocao = usuario.getAdocoesAnunciadas();
        try {    
            adocao.setAtivo(true);
            adocao.setUsuarioAnunciador(usuario); 
            servicosAnimal.cadastrarAnimal(animal, nomeClassificacao, nomeRaca);          
            adocao.setAnimal(servicosAnimal.consultarAnimalPorCodigo(animal.getCodigo()));
            repositorioAdocao.save(adocao);                                             
            listaAdocao.add(adocao);
            usuario.setAdocoesAnunciadas(listaAdocao);
            servicosUsuario.atualizarUsuario(usuario, email); 
        } catch (UsuarioInexistenteException | ClassificacaoInexistenteException | RacaInexistenteException e) {
            throw e;
        } catch (AnimalInexistenteException ex) {
            Logger.getLogger(ServicosAdocaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void excluirAdocao(Long id) throws AdocaoInexistenteException {
        Adocao adocao = buscarAdocaoPorCodigo(id);
        if(adocao==null){
            throw new AdocaoInexistenteException();
        }else{
           adocao.setAtivo(false);
           repositorioAdocao.save(adocao);
        } 
    }

    @Override
    public List<ListarAdocao> listarAdocao() {
        List<Adocao> adocao = (List<Adocao>) repositorioAdocao.findAll();
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
                ListarAdocao dl = new ListarAdocao();
                dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
                dl.setDataInteressado(adocao.get(i).getDataInteressado());
                dl.setCodigo(adocao.get(i).getCodigo());
                dl.setAnimal(adocao.get(i).getAnimal().getNome());
                dl.setUsuarioAnunciador(adocao.get(i).getUsuarioAnunciador().getNome());
                if(adocao.get(i).getInteressados() != null){
                    List<String> interessados = new ArrayList<String>();
                    for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                        String nome = adocao.get(i).getInteressados().get(j).getNome();
                        interessados.add(nome);
                    }
                    dl.setInteressados(interessados);
                }
                if(adocao.get(i).getUsuarioEscolhido()!= null){
                    dl.setUsuarioEscolhido(adocao.get(i).getUsuarioEscolhido().getNome());
                }
                listaAdocao.add(dl); 
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorAnimal(String nomeAnimal) {
        List<ListarAdocao> adocao = repositorioAdocao.findByAnimal(nomeAnimal); 
        return adocao; 
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorClassificacao(String nomeClassificacao) {
        List<Adocao> adocao = (List<Adocao>) repositorioAdocao.findByClassificacao(nomeClassificacao);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setUsuarioAnunciador(adocao.get(i).getUsuarioAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getUsuarioEscolhido()!= null){
                dl.setUsuarioEscolhido(adocao.get(i).getUsuarioEscolhido().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca) {
        List<Adocao> adocao = (List<Adocao>) repositorioAdocao.findByRaca(nomeRaca);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setUsuarioAnunciador(adocao.get(i).getUsuarioAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getUsuarioEscolhido()!= null){
                dl.setUsuarioEscolhido(adocao.get(i).getUsuarioEscolhido().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorUsuarioAnunciador(String nomeUsuario) {
         List<Adocao> adocao = (List<Adocao>) repositorioAdocao.findByUsuarioAnunciador(nomeUsuario);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setUsuarioAnunciador(adocao.get(i).getUsuarioAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getUsuarioEscolhido()!= null){
                dl.setUsuarioEscolhido(adocao.get(i).getUsuarioEscolhido().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarPorUsuarioEscolhido(String nomeUsuario) {
         List<Adocao> adocao = (List<Adocao>) repositorioAdocao.findByUsuarioEscolhido(nomeUsuario);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setUsuarioAnunciador(adocao.get(i).getUsuarioAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getUsuarioEscolhido()!= null){
                dl.setUsuarioEscolhido(adocao.get(i).getUsuarioEscolhido().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarPorData(Date data) {
         List<Adocao> adocao = (List<Adocao>) repositorioAdocao.findByDataAnuncio(data);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setUsuarioAnunciador(adocao.get(i).getUsuarioAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getUsuarioEscolhido()!= null){
                dl.setUsuarioEscolhido(adocao.get(i).getUsuarioEscolhido().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public void interesseAdocao(String email, long codigo) throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoExistenteException {
        Adocao adocao = new Adocao();
        Usuario usuario = new Usuario();
        List<Adocao> listaAdocao =  new ArrayList<Adocao>();
        List<Usuario> listaUsuario =  new ArrayList<Usuario>();
        
        adocao = repositorioAdocao.findOne(codigo);
        if(adocao.getUsuarioEscolhido()!=null){
           throw new AdocaoExistenteException();
        } else{
            usuario = servicosUsuario.BuscarUsuarioPorEmail(email);
            if(usuario.getEmail().equals(adocao.getUsuarioAnunciador().getEmail())){
                throw new ProprioUsuarioAnunciadorException();
            }
            else{
                listaAdocao = usuario.getAdocoesInteressadas();
                listaAdocao.add(adocao);
                listaUsuario = adocao.getInteressados();
                listaUsuario.add(usuario);
                adocao.setInteressados(listaUsuario);
                servicosUsuario.atualizarUsuario(usuario, email);
                if(adocao.getDataInteressado()==null){
                    adocao.setDataInteressado(new Date());
                }
                repositorioAdocao.save(adocao);
            }
        }
    }

    @Override
    public void realizarAdocao() throws UsuarioInexistenteException {
        List<Adocao> listaAdocao = (List<Adocao>) repositorioAdocao.findAll();
        for(int i= 0; i< listaAdocao.size(); i++){ 
            if(listaAdocao.get(i).getDataInteressado()!=null && listaAdocao.get(i).getUsuarioEscolhido()==null){
                Usuario usuarioGanhador = new Usuario();
                List<Usuario> listaUsuarioInteressado = listaAdocao.get(i).getInteressados();
               
            }
        }
    }

    @Override
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException {
        Adocao adocaoAtualizar =  repositorioAdocao.findOne(codigo);
        if(adocaoAtualizar==null){
           throw new AdocaoInexistenteException();
        }
        return adocaoAtualizar;
    }
    
}
