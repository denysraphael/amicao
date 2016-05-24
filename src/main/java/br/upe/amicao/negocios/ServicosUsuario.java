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
import java.io.Serializable;
import java.util.List;

public interface ServicosUsuario extends Serializable{
    
    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException, UsuarioInexistenteException;
    public void atualizarUsuario(Usuario usuario, String emailAtualizar) throws UsuarioInexistenteException;
    public void excluirUsuario(String email) throws UsuarioInexistenteException;
    public List<ListarUsuario> listarUsuario();
    public Usuario BuscarUsuarioPorEmail(String email) throws UsuarioInexistenteException;
    public List<ListarUsuario> BuscarUsuarioPorNome(String nome) throws UsuarioInexistenteException;
    
}
