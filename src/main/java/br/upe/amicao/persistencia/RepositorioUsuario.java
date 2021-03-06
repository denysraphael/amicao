/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.persistencia;

import br.upe.amicao.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {
    
    public Usuario findByEmail(String email);
    
    public Usuario findByEmailAndSenha(String email, String senha);
    
    //@Query("Select u from Usuario u where u.nome like '%nomeUsuario%'")
    //public List<Usuario> buscarPorNome(@Param("nomeUsuario") String nomeUsuario);
    public List<Usuario> findByNome(String nome);
}
