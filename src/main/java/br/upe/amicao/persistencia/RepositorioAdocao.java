/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.persistencia;

import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.negocios.ListarAdocao;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author -Denys
 */
public interface RepositorioAdocao extends CrudRepository<Adocao, Long>{
    
    @Query("select new br.upe.amicao.listar.AdocaoListar(a) from Adocao a where a.animal.nome=:nomeAnimal")
    public List<ListarAdocao> findByAnimal(@Param("nomeAnimal") String nomeAnimal);
    @Query("select a from Adocao a where a.animal.classificacao.nome=:nomeClassificacao ")
    public List<Adocao> findByClassificacao(@Param("nomeClassificacao") String nomeClassificacao); 
    @Query("select a from Adocao a where a.animal.raca.nome=:nomeRaca ")
    public List<Adocao> findByRaca(@Param("nomeRaca") String nomeRaca); 
    @Query("select a from Adocao a where a.dataAnuncio=:dataAnuncio")
    public List<Adocao> findByDataAnuncio(@Param("dataAnuncio") Date dataAnuncio);
    @Query("select a from Adocao a where a.usuarioEscolhido.nome=:nomeUsuario")
    public List<Adocao> findByUsuarioEscolhido(@Param("nomeUsuario") String nomeUsuario);
    @Query("select a from Adocao a where a.usuarioAnunciador.nome=:nomeUsuario")
    public List<Adocao> findByUsuarioAnunciador(@Param("nomeUsuario") String nomeUsuario);
}
