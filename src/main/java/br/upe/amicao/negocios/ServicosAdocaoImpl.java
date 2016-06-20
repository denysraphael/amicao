/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.negocios;

import br.upe.amicao.listar.ListarAdocao;
import br.upe.amicao.exceptions.UsuarioInexistenteException;
import br.upe.amicao.exceptions.ProprioUsuarioAnunciadorException;
import br.upe.amicao.exceptions.AnimalInexistenteException;
import br.upe.amicao.exceptions.AdocaoInexistenteException;
import br.upe.amicao.entidades.Adocao;
import br.upe.amicao.entidades.Animal;
import br.upe.amicao.entidades.Raca;
import br.upe.amicao.entidades.Usuario;
import br.upe.amicao.exceptions.AdocaoExistenteException;
import br.upe.amicao.exceptions.AdocaoJaRealizadaException;
import br.upe.amicao.exceptions.UsuarioNaoInteressadoExceptions;
import br.upe.amicao.persistencia.RepositorioAdocao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicosAdocaoImpl implements ServicosAdocao {

    @Autowired
    private RepositorioAdocao repAdocao;

    @Autowired
    private ServicosUsuario servicosUsuario;

    @Autowired
    private ServicosAnimal servicosAnimal;

    @Override
    @Transactional(rollbackFor = AdocaoExistenteException.class)
    public void cadastrarAdocao(Adocao adocao) throws AdocaoExistenteException {
        try {
            List<Adocao> adocoes = this.buscarAdocaoPorAnimal(adocao.getAnimal());

            if (!adocoes.isEmpty()) {
                for (Adocao a : adocoes) {
                    if (a.isAtivo()) {
                        throw new AdocaoExistenteException();
                    }
                }
            }
        } catch (AdocaoInexistenteException e) {
            repAdocao.save(adocao);
        }
    }

    @Override
    @Transactional(rollbackFor = AdocaoInexistenteException.class)
    public void atualizarAdocao(Adocao adocao) throws AdocaoInexistenteException {
        Adocao adocaoAtualizar = this.buscarAdocaoPorCodigo(adocao.getCodigo());

        if (adocaoAtualizar != null) {
            adocaoAtualizar.setDescricao(adocao.getDescricao());
            adocaoAtualizar.setAnimal(adocao.getAnimal());
            adocaoAtualizar.setAnunciador(adocao.getAnunciador());
            adocaoAtualizar.setAdotante(adocao.getAdotante());
            adocaoAtualizar.setInteressados(adocao.getInteressados());
            adocaoAtualizar.setDataAnuncio(adocao.getDataAnuncio());
            adocaoAtualizar.setDataAdocao(adocao.getDataAdocao());
            adocaoAtualizar.setAtivo(adocao.isAtivo());

            repAdocao.save(adocaoAtualizar);
        }
    }

    @Override
    @Transactional(rollbackFor = AdocaoInexistenteException.class)
    public void desativarAdocao(Adocao adocao) throws AdocaoInexistenteException {
        Adocao adocaoDesativar = this.buscarAdocaoPorCodigo(adocao.getCodigo());
        
        if (adocaoDesativar != null) {
            adocaoDesativar.setAtivo(false);
            
            repAdocao.save(adocaoDesativar);
        }
    }

    @Override
    public List<Adocao> listarAdocoes() {
        return (List<Adocao>) repAdocao.findAll();
    }
    
    @Override
    public Adocao buscarAdocaoPorCodigo(Long codigo) throws AdocaoInexistenteException {
        Adocao adocaoAtualizar = repAdocao.findOne(codigo);
        if (adocaoAtualizar == null) {
            throw new AdocaoInexistenteException();
        }
        return adocaoAtualizar;
    }
    
    @Override
    public List<Adocao> buscarAdocaoPorDescricao(String descricao) throws AdocaoInexistenteException {
        List<Adocao> temp = repAdocao.findByDescricao(descricao);
        
        if (temp.isEmpty())
            throw new AdocaoInexistenteException();
        
        return temp;
    }

    @Override
    public List<Adocao> buscarAdocaoPorAnimal(Animal animal) throws AdocaoInexistenteException {
        List<Adocao> temp = repAdocao.findByAnimal(animal);
        
        if (temp.isEmpty())
            throw new AdocaoInexistenteException();
        
        return temp;
    }
    
    @Override
    public List<Adocao> buscarAdocaoPorNomeAnimal(String nomeAnimal) throws AdocaoInexistenteException {
        List<Adocao> temp = repAdocao.buscarPorNomeAnimal(nomeAnimal);
        
        if (temp.isEmpty())
            throw new AdocaoInexistenteException();
        
        return temp;
    }

    @Override
    public List<Adocao> buscarAdocaoPorAnunciador(Usuario anunciador) throws AdocaoInexistenteException {
        List<Adocao> temp = repAdocao.findByAnunciador(anunciador);

        if (temp.isEmpty()) {
            throw new AdocaoInexistenteException();
        }

        return temp;
    }
    
    @Override
    public List<Adocao> buscarAdocaoPorAdotante(Usuario adotante) throws AdocaoInexistenteException {
        List<Adocao> temp = repAdocao.findByAdotante(adotante);

        if (temp.isEmpty()) {
            throw new AdocaoInexistenteException();
        }

        return temp;
    }
    
    @Override
    public List<Adocao> buscarAdocaoPorRaca(Raca raca) throws AdocaoInexistenteException {
        List<Adocao> temp = repAdocao.buscarPorRaca(raca.getNome());
        
        if (temp.isEmpty()) {
            throw new AdocaoInexistenteException();
        }
        
        return temp;
    }

    /*@Override
    public List<ListarAdocao> buscarPorData(Date data) {
        List<Adocao> adocao = (List<Adocao>) repAdocao.buscarPorDataAnuncio(data);
        List<ListarAdocao> listaAdocao = new ArrayList<ListarAdocao>();

        for (int i = 0; i < adocao.size(); i++) {
            if (adocao.get(i).isAtivo()) {
                ListarAdocao dl = new ListarAdocao();
                dl.setDataAnuncio(adocao.get(i).getDataAnuncio());
                dl.setDataInteressado(adocao.get(i).getDataInteressado());
                dl.setCodigo(adocao.get(i).getCodigo());
                dl.setAnimal(adocao.get(i).getAnimal().getNome());
                dl.setAnunciador(adocao.get(i).getAnunciador().getNome());
                if (adocao.get(i).getInteressados() != null) {
                    List<String> interessados = new ArrayList<String>();
                    for (int j = 0; j < adocao.get(i).getInteressados().size(); j++) {
                        String nome = adocao.get(i).getInteressados().get(j).getNome();
                        interessados.add(nome);
                    }
                    dl.setInteressados(interessados);
                }
                if (adocao.get(i).getAdotante() != null) {
                    dl.setAdotante(adocao.get(i).getAdotante().getNome());
                }
                listaAdocao.add(dl);
            }
        }
        return listaAdocao;
    }

    @Override
    @Transactional(rollbackFor = UsuarioInexistenteException.class)
    public void interesseAdocao(String email, long codigo) throws UsuarioInexistenteException, ProprioUsuarioAnunciadorException, AdocaoJaRealizadaException {
        Adocao adocao = new Adocao();
        Usuario usuario = new Usuario();
        List<Adocao> listaAdocao = new ArrayList<Adocao>();
        List<Usuario> listaUsuario = new ArrayList<Usuario>();

        adocao = repAdocao.findOne(codigo);
        if (adocao.getAdotante() != null) {
            throw new AdocaoJaRealizadaException();
        } else {
            usuario = servicosUsuario.BuscarUsuarioPorEmail(email);
            if (usuario.getEmail().equals(adocao.getAnunciador().getEmail())) {
                throw new ProprioUsuarioAnunciadorException();
            } else {
                listaAdocao = usuario.getAdocoesInteressadas();
                listaAdocao.add(adocao);
                listaUsuario = adocao.getInteressados();
                listaUsuario.add(usuario);
                adocao.setInteressados(listaUsuario);
                servicosUsuario.atualizarUsuario(usuario, email);
                repAdocao.save(adocao);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = UsuarioNaoInteressadoExceptions.class)
    public void escolherAdotante(Long codigo, String emailAdotante) throws AdocaoInexistenteException, UsuarioNaoInteressadoExceptions {
        Adocao adocao = repAdocao.findOne(codigo);
        if (adocao == null) {
            throw new AdocaoInexistenteException();
        } else if (adocao.getAdotante() == null) {
            Usuario usuarioEscolhido = new Usuario();
            List<Usuario> listaUsuarioInteressado = adocao.getInteressados();
            if (listaUsuarioInteressado.contains(emailAdotante) == false) {
                throw new UsuarioNaoInteressadoExceptions();
            } else {
                for (int i = 0; i < listaUsuarioInteressado.size(); i++) {
                    if (listaUsuarioInteressado.get(i).getEmail() == emailAdotante) {
                        usuarioEscolhido = listaUsuarioInteressado.get(0);
                    }

                }
            }
            List<Adocao> adocoesRealizadas = usuarioEscolhido.getAdocoesRealizadas();
            adocoesRealizadas.add(adocao);
            usuarioEscolhido.setAdocoesRealizadas(adocoesRealizadas);
            try {
                servicosUsuario.atualizarUsuario(usuarioEscolhido, usuarioEscolhido.getEmail());
            } catch (UsuarioInexistenteException ex) {
                Logger.getLogger(ServicosAdocaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            adocao.setAdotante(usuarioEscolhido);
            repAdocao.save(adocao);
        }
    }*/

}
