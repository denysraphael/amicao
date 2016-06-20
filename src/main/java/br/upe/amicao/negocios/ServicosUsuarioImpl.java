/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.persistencia.RepositorioUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicosUsuarioImpl implements ServicosUsuario {

    @Autowired
    private RepositorioUsuario repUsuario;

    @Override
    @Transactional(rollbackFor = UsuarioExistenteException.class)
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException {
        try {
            Usuario u = this.findByEmail(usuario.getEmail());
            if (u != null) {
                throw new UsuarioExistenteException();
            }
        } catch (UsuarioInexistenteException e) {
            repUsuario.save(usuario);
        }
    }

    @Override
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void atualizarUsuario(Usuario usuario) throws UsuarioInexistenteException {
        Usuario usuarioAtualizar = this.findByEmail(usuario.getEmail());
        
        if (usuarioAtualizar != null) {
            usuarioAtualizar.setNome(usuario.getNome());
            usuarioAtualizar.setSenha(usuario.getSenha());
            usuarioAtualizar.setTelefone(usuario.getTelefone());
            usuarioAtualizar.setAnimais(usuario.getAnimais());
            usuarioAtualizar.setAdocoesAnunciadas(usuario.getAdocoesAnunciadas());
            usuarioAtualizar.setAdocoesInteressadas(usuario.getAdocoesInteressadas());
            usuarioAtualizar.setAdocoesRealizadas(usuario.getAdocoesRealizadas());
            usuarioAtualizar.setAtivo(usuario.isAtivo());
            repUsuario.save(usuarioAtualizar);
        }
    }

    @Override
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void desativarUsuario(Usuario usuario) throws UsuarioInexistenteException {
        Usuario usuarioAtualizar = repUsuario.findOne(usuario.getCodigo());
        if (usuarioAtualizar == null) {
            throw new UsuarioInexistenteException();
        }
        usuarioAtualizar.setAtivo(false);
        repUsuario.save(usuarioAtualizar);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return (List<Usuario>) repUsuario.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) throws UsuarioInexistenteException {
        return this.findByEmail(email);
    }

    @Override
    public List<Usuario> buscarUsuarioPorNome(String nome) throws UsuarioInexistenteException {
        return repUsuario.findByNome(nome);
    }

    private Usuario findByEmail(String email) throws UsuarioInexistenteException {
        Usuario u = repUsuario.findByEmail(email);

        if (u == null) {
            throw new UsuarioInexistenteException();
        }

        return u;
    }
}
