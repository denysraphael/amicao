/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.listar.ListarUsuario;
import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.persistencia.RepositorioUsuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author -Denys
 */
@Service
public class ServicosUsuarioImpl implements ServicosUsuario{

    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Transactional(rollbackFor = UsuarioExistenteException.class)
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException, UsuarioInexistenteException {
        Usuario us = repositorioUsuario.findByEmail(usuario.getEmail());
        if(us!=null){
            if(!us.isAtivo()){
                us.setAtivo(true);
                us.setNome(usuario.getNome());
                us.setTelefone(usuario.getTelefone());
                us.setSenha(usuario.getSenha());
                atualizarUsuario(us, us.getEmail());
            }else{
                throw new UsuarioExistenteException();
            }
        }else{
            repositorioUsuario.save(usuario);
        }
    }

    @Override
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void atualizarUsuario(Usuario usuario, String emailAtualizar) throws UsuarioInexistenteException {
        Usuario usuarioAtualizar = repositorioUsuario.findByEmail(usuario.getEmail());
        if(usuarioAtualizar==null){
            throw new UsuarioInexistenteException();
        }
        else{
            usuarioAtualizar.setNome(usuario.getNome());
            usuarioAtualizar.setSenha(usuario.getSenha());
            usuarioAtualizar.setTelefone(usuario.getTelefone());
            usuarioAtualizar.setAnimais(usuario.getAnimais());
            usuarioAtualizar.setAdocoesAnunciadas(usuario.getAdocoesAnunciadas());
            usuarioAtualizar.setAdocoesInteressadas(usuario.getAdocoesInteressadas());
            usuarioAtualizar.setAdocoesRealizadas(usuario.getAdocoesRealizadas());
            usuarioAtualizar.setAtivo(usuario.isAtivo());
            repositorioUsuario.save(usuarioAtualizar);
        }
    }

    @Override
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void excluirUsuario(String email) throws UsuarioInexistenteException {
        Usuario usuarioAtualizar = repositorioUsuario.findByEmail(email);
        if(usuarioAtualizar==null)
            throw new UsuarioInexistenteException();
        usuarioAtualizar.setAtivo(false);
        repositorioUsuario.save(usuarioAtualizar);
    }

    @Override
    public List<ListarUsuario> listarUsuario() {
        List<ListarUsuario> retorno = new ArrayList<ListarUsuario>();
        List<Usuario> pesquisa = (List<Usuario>) repositorioUsuario.findAll();
        for(int i = 0; i < pesquisa.size(); i++){
            if(pesquisa.get(i).isAtivo()){
                ListarUsuario lu = new ListarUsuario();
                lu.setCodigo(pesquisa.get(i).getCodigo());
                lu.setNome(pesquisa.get(i).getNome());
                lu.setTelefone(pesquisa.get(i).getTelefone());
                lu.setEmail(pesquisa.get(i).getEmail());
                lu.setSenha(pesquisa.get(i).getSenha());
                 if(pesquisa.get(i).getAnimais()!=null){
                    List<String> animais = new ArrayList<String>();
                    for(int j = 0; j < pesquisa.get(i).getAnimais().size(); j++){
                        String nome = pesquisa.get(i).getAnimais().get(j).getNome();
                        animais.add(nome);
                    }
                    lu.setAnimais(animais);
                }
                if(pesquisa.get(i).getAdocoesAnunciadas()!=null){
                    List<String> adocoesAnunciadas = new ArrayList<String>();
                    for(int j = 0; j < pesquisa.get(i).getAdocoesAnunciadas().size(); j++){
                        String nome = pesquisa.get(i).getAdocoesAnunciadas().get(j).getAnimal().getNome();
                        adocoesAnunciadas.add(nome);
                    }
                    lu.setAdocoesAnunciadas(adocoesAnunciadas);
                }
                if(pesquisa.get(i).getAdocoesInteressadas()!=null){
                    List<String> adocoesInteressadas = new ArrayList<String>();
                    for(int j = 0; j < pesquisa.get(i).getAdocoesInteressadas().size(); j++){
                        String nome = pesquisa.get(i).getAdocoesInteressadas().get(j).getAnimal().getNome();
                        adocoesInteressadas.add(nome);
                    }
                    lu.setAdocoesInteressadas(adocoesInteressadas);
                }
                if(pesquisa.get(i).getAdocoesRealizadas()!=null){
                    List<String> adocoesRealizadas = new ArrayList<String>();
                    for(int j = 0; j < pesquisa.get(i).getAdocoesRealizadas().size(); j++){
                        String nome = pesquisa.get(i).getAdocoesRealizadas().get(j).getAnimal().getNome();
                        adocoesRealizadas.add(nome);
                    }
                    lu.setAdocoesRealizadas(adocoesRealizadas);
                }
                retorno.add(lu);
            }
        }
        return retorno;
    }

    @Override
    public Usuario BuscarUsuarioPorEmail(String email) throws UsuarioInexistenteException {
        Usuario usuarioAtualizar = repositorioUsuario.findByEmail(email);
        if(usuarioAtualizar==null || !usuarioAtualizar.isAtivo())
            throw new UsuarioInexistenteException();
        return usuarioAtualizar;
    }

    @Override
    public List<ListarUsuario> BuscarUsuarioPorNome(String nome) throws UsuarioInexistenteException {
        List<ListarUsuario> retorno = new ArrayList<ListarUsuario>();
        List<Usuario> pesquisa = repositorioUsuario.buscarPorNome(nome);
         for(int i = 0; i < pesquisa.size(); i++){
            if(pesquisa.get(i).isAtivo()){
                ListarUsuario lu = new ListarUsuario();
                lu.setCodigo(pesquisa.get(i).getCodigo());
                lu.setNome(pesquisa.get(i).getNome());
                lu.setTelefone(pesquisa.get(i).getTelefone());
                lu.setEmail(pesquisa.get(i).getEmail());
                lu.setSenha(pesquisa.get(i).getSenha());
                 if(pesquisa.get(i).getAnimais()!=null){
                    List<String> animais = new ArrayList<String>();
                    for(int j = 0; j < pesquisa.get(i).getAnimais().size(); j++){
                        String nome2 = pesquisa.get(i).getAnimais().get(j).getNome();
                        animais.add(nome);
                    }
                    lu.setAnimais(animais);
                }
                if(pesquisa.get(i).getAdocoesAnunciadas()!=null){
                    List<String> adocoesAnunciadas = new ArrayList<String>();
                    for(int j = 0; j < pesquisa.get(i).getAdocoesAnunciadas().size(); j++){
                        String nome2 = pesquisa.get(i).getAdocoesAnunciadas().get(j).getAnimal().getNome();
                        adocoesAnunciadas.add(nome);
                    }
                    lu.setAdocoesAnunciadas(adocoesAnunciadas);
                }
                if(pesquisa.get(i).getAdocoesInteressadas()!=null){
                    List<String> adocoesInteressadas = new ArrayList<String>();
                    for(int j = 0; j < pesquisa.get(i).getAdocoesInteressadas().size(); j++){
                        String nome2 = pesquisa.get(i).getAdocoesInteressadas().get(j).getAnimal().getNome();
                        adocoesInteressadas.add(nome);
                    }
                    lu.setAdocoesInteressadas(adocoesInteressadas);
                }
                if(pesquisa.get(i).getAdocoesRealizadas()!=null){
                    List<String> adocoesRealizadas = new ArrayList<String>();
                    for(int j = 0; j < pesquisa.get(i).getAdocoesRealizadas().size(); j++){
                        String nome2 = pesquisa.get(i).getAdocoesRealizadas().get(j).getAnimal().getNome();
                        adocoesRealizadas.add(nome);
                    }
                    lu.setAdocoesRealizadas(adocoesRealizadas);
                }
                retorno.add(lu);
            }
        }
        return retorno;
    }   
}
