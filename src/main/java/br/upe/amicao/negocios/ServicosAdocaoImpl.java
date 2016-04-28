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
import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AdocaoJaRealizadaException;
import br.upe.amicao.persistencia.RepositorioAdocao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void cadastrarAdocao(Adocao adocao, Animal animal, String email, String nomeClassificacao, String nomeRaca) throws UsuarioInexistenteException, ClassificacaoInexistenteException, RacaInexistenteException {
        Usuario usuario = servicosUsuario.BuscarUsuarioPorEmail(email);
        List<Adocao> listaAdocao = usuario.getAdocoesAnunciadas();
        try {    
            adocao.setAtivo(true);
            adocao.setAnunciador(usuario); 
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
    @Transactional(rollbackFor = AdocaoInexistenteException.class)
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
                dl.setAnunciador(adocao.get(i).getAnunciador().getNome());
                if(adocao.get(i).getInteressados() != null){
                    List<String> interessados = new ArrayList<String>();
                    for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                        String nome = adocao.get(i).getInteressados().get(j).getNome();
                        interessados.add(nome);
                    }
                    dl.setInteressados(interessados);
                }
                if(adocao.get(i).getAdotante()!= null){
                    dl.setAdotante(adocao.get(i).getAdotante().getNome());
                }
                listaAdocao.add(dl); 
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorAnimal(String nomeAnimal) {
        List<ListarAdocao> adocao = repositorioAdocao.buscarPorAnimal(nomeAnimal); 
        return adocao; 
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorClassificacao(String nomeClassificacao) {
        List<Adocao> adocao = (List<Adocao>) repositorioAdocao.buscarPorClassificacao(nomeClassificacao);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setAnunciador(adocao.get(i).getAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getAdotante()!= null){
                dl.setAdotante(adocao.get(i).getAdotante().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca) {
        List<Adocao> adocao = (List<Adocao>) repositorioAdocao.buscarPorRaca(nomeRaca);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setAnunciador(adocao.get(i).getAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getAdotante()!= null){
                dl.setAdotante(adocao.get(i).getAdotante().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorAnunciador(String nomeAnunciador){
         List<Adocao> adocao = (List<Adocao>) repositorioAdocao.buscarPorAnunciador(nomeAnunciador);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setAnunciador(adocao.get(i).getAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getAdotante()!= null){
                dl.setAdotante(adocao.get(i).getAdotante().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarAdocaoPorAdotante(String nomeAdotante) {
         List<Adocao> adocao = (List<Adocao>) repositorioAdocao.buscarPorAdotante(nomeAdotante);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setAnunciador(adocao.get(i).getAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getAdotante()!= null){
                dl.setAdotante(adocao.get(i).getAdotante().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    public List<ListarAdocao> buscarPorData(Date data) {
         List<Adocao> adocao = (List<Adocao>) repositorioAdocao.buscarPorDataAnuncio(data);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();
        
        for(int i = 0; i < adocao.size(); i++){
            if(adocao.get(i).isAtivo()){
            ListarAdocao dl = new ListarAdocao();
            dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
            dl.setDataInteressado(adocao.get(i).getDataInteressado());
            dl.setCodigo(adocao.get(i).getCodigo());
            dl.setAnimal(adocao.get(i).getAnimal().getNome());
            dl.setAnunciador(adocao.get(i).getAnunciador().getNome());
            if(adocao.get(i).getInteressados() != null){
                List<String> interessados = new ArrayList<String>();
                for(int j = 0; j < adocao.get(i).getInteressados().size(); j++){
                    String nome = adocao.get(i).getInteressados().get(j).getNome();
                    interessados.add(nome);
                }
                dl.setInteressados(interessados);
            }
            if(adocao.get(i).getAdotante()!= null){
                dl.setAdotante(adocao.get(i).getAdotante().getNome());
            }
            listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void interesseAdocao(String email,long codigo)throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoJaRealizadaException {
        Adocao adocao = new Adocao();
        Usuario usuario = new Usuario();
        List<Adocao> listaAdocao =  new ArrayList<Adocao>();
        List<Usuario> listaUsuario =  new ArrayList<Usuario>();
        
        adocao = repositorioAdocao.findOne(codigo);
        if(adocao.getAdotante()!=null){
           throw new AdocaoJaRealizadaException();
        } else{
            usuario = servicosUsuario.BuscarUsuarioPorEmail(email);
            if(usuario.getEmail().equals(adocao.getAnunciador().getEmail())){
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
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void escolherAdotante() throws UsuarioInexistenteException{
        List<Adocao> listaAdocao = (List<Adocao>) repositorioAdocao.findAll();
        for(int i= 0; i< listaAdocao.size(); i++){ 
            if(listaAdocao.get(i).getDataInteressado()!=null && listaAdocao.get(i).getAdotante()==null){
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
