/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.UsuarioExistenteException;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.entidades.Usuario;
import java.io.Serializable;
import java.util.List;

public interface ServicosUsuario extends Serializable {

    /**
     * Cadastrar um Usuário
     *
     * @param usuario
     * @throws UsuarioExistenteException
     */
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException;
    
    /**
     * Atualiza um Usuário
     *
     * @param usuario
     * @throws UsuarioInexistenteException
     */
    public void atualizarUsuario(Usuario usuario) throws UsuarioInexistenteException;

    /**
     * Exclui um Usuário
     *
     * @param usuario
     * @throws UsuarioInexistenteException
     */
    public void desativarUsuario(Usuario usuario) throws UsuarioInexistenteException;

    /**
     * Lista todos os usuários
     *
     * @return Lista de Usuários
     */
    public List<Usuario> listarUsuario();

    /**
     * Busca um usuário por código
     * 
     * @param codigo
     * @return
     * @throws UsuarioInexistenteException 
     */
    public Usuario buscarUsuarioPorCodigo(Long codigo) throws UsuarioInexistenteException;
    
    /**
     * Busca um Usuário por email
     *
     * @param email
     * @return Usuário
     * @throws UsuarioInexistenteException
     */
    public Usuario buscarUsuarioPorEmail(String email) throws UsuarioInexistenteException;

    /**
     * Busca um usuário por email e senha
     * 
     * @param email
     * @param senha
     * @return
     * @throws UsuarioInexistenteException 
     */
    public Usuario buscarUsuarioPorEmailESenha(String email, String senha) throws UsuarioInexistenteException;
    
    /**
     * Busca Usuários pelo Nome
     *
     * @param nome
     * @return
     * @throws UsuarioInexistenteException
     */
    public List<Usuario> buscarUsuarioPorNome(String nome) throws UsuarioInexistenteException;

}
