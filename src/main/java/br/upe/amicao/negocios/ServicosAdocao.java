/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.exceptions.AdocaoJaRealizadaException;
import br.upe.amicao.exceptions.ProprioUsuarioAnunciadorException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AdocaoExistenteException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

// Fazer um pra buscar pro Raça, buscando pelo método de buscar por Raça de ANIMAL
public interface ServicosAdocao extends Serializable {

    public void cadastrarAdocao(Adocao adocao) throws AdocaoExistenteException;
    
    public void atualizarAdocao(Adocao adocao) throws AdocaoInexistenteException;

    public void desativarAdocao(Adocao adocao) throws AdocaoInexistenteException;

    public List<Adocao> listarAdocoes();
    
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException;
    
    public List<Adocao> buscarAdocaoPorDescricao(String descricao) throws AdocaoInexistenteException;

    public List<Adocao> buscarAdocaoPorAnimal(Animal animal) throws AdocaoInexistenteException;
    
    public List<Adocao> buscarAdocaoPorNomeAnimal(String nomeAnimal) throws AdocaoInexistenteException;

    //public List<Adocao> buscarAdocaoPorClassificacao(String nomeClassificacao);

    //public List<ListarAdocao> buscarAdocaoPorRaca(String nomeRaca);

    public List<Adocao> buscarAdocaoPorAnunciador(Usuario anunciador) throws AdocaoInexistenteException;

    public List<Adocao> buscarAdocaoPorAdotante(Usuario adotante) throws AdocaoInexistenteException;

    //public List<ListarAdocao> buscarPorData(Date data);
    
    public List<Adocao> buscarAdocaoPorRaca(String nomeRaca) throws AdocaoInexistenteException;

    //public void interesseAdocao(String email, long codigo) throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoJaRealizadaException;

    //public void escolherAdotante(Long codigo, String nomeAdotante) throws AdocaoInexistenteException, UsuarioNaoInteressadoExceptions;
}
