/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.persistencia.RepositorioUsuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicosUsuarioImpl implements ServicosUsuario {

    @Autowired
    private RepositorioUsuario repUsuario;

    @Autowired
    private ServicosAdocao servicosAdocao;

    @Override
    @Transactional(rollbackFor = UsuarioExistenteException.class)
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException {
        try {
            Usuario u = this.buscarUsuarioPorEmail(usuario.getEmail());
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
        Usuario usuarioAtualizar = this.buscarUsuarioPorCodigo(usuario.getCodigo());

        if (usuarioAtualizar != null) {
            usuarioAtualizar.setNome(usuario.getNome());
            usuarioAtualizar.setEmail(usuario.getEmail());
            usuarioAtualizar.setSenha(usuario.getSenha());
            usuarioAtualizar.setTelefone(usuario.getTelefone());
            usuarioAtualizar.setAnimais(usuario.getAnimais());
            usuarioAtualizar.setAdocoesAnunciadas(usuario.getAdocoesAnunciadas());
            usuarioAtualizar.setAdocoesRealizadas(usuario.getAdocoesRealizadas());
            usuarioAtualizar.setAtivo(usuario.isAtivo());
            repUsuario.save(usuarioAtualizar);
        }
    }

    @Override
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void desativarUsuario(Usuario usuario) throws UsuarioInexistenteException {
        try {
            Usuario usuarioAtualizar = repUsuario.findOne(usuario.getCodigo());
            if (usuarioAtualizar == null) {
                throw new UsuarioInexistenteException();
            }

            List<Adocao> adocoes = this.servicosAdocao.buscarAdocaoPorAnunciador(usuario);

            for (Adocao a : adocoes) {
                a.setAtivo(false);
                this.servicosAdocao.atualizarAdocao(a);
            }
            
            usuarioAtualizar.setAtivo(false);
            repUsuario.save(usuarioAtualizar);
        } catch (AdocaoInexistenteException ex) {
            Logger.getLogger(ServicosUsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Usuario> listarUsuario() {
        return (List<Usuario>) repUsuario.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorCodigo(Long codigo) throws UsuarioInexistenteException {
        Usuario u = repUsuario.findOne(codigo);

        if (u == null) {
            throw new UsuarioInexistenteException();
        }

        return u;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) throws UsuarioInexistenteException {
        Usuario u = repUsuario.findByEmail(email);

        if (u == null) {
            throw new UsuarioInexistenteException();
        }

        return u;
    }

    @Override
    public Usuario buscarUsuarioPorEmailESenha(String email, String senha) throws UsuarioInexistenteException {
        Usuario u = repUsuario.findByEmailAndSenha(email, senha);

        if (u == null) {
            throw new UsuarioInexistenteException();
        }

        return u;
    }

    @Override
    public List<Usuario> buscarUsuarioPorNome(String nome) throws UsuarioInexistenteException {
        return repUsuario.findByNome(nome);
    }
}
