/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FilmesDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Filmes;
import br.edu.ifsul.modelo.Pessoas;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author romulo
 */
@Named(value = "controleFilmes")
@ViewScoped
public class ControleFilmes implements Serializable {

    @EJB
    private FilmesDAO<Filmes> dao;
    private Filmes objeto;
    private Boolean editando;

    @EJB
    private PessoaDAO<Pessoas> daoPessoas;
    private Boolean novoObjeto;

    public ControleFilmes() {
        editando = false;
    }

    public String listar() {
        editando = false;
        return "/privado/filmes/listar?faces-redirect=true";
    }

    public void novo() {
        novoObjeto = true;
        editando = true;
        objeto = new Filmes();

    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;

        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }    

    public FilmesDAO<Filmes> getDao() {
        return dao;
    }

    public void setDao(FilmesDAO<Filmes> dao) {
        this.dao = dao;
    }

    public Filmes getObjeto() {
        return objeto;
    }

    public void setObjeto(Filmes objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }


    public PessoaDAO<Pessoas> getDaoPessoas() {
        return daoPessoas;
    }

    public void setDaoPessoas(PessoaDAO<Pessoas> daoPessoas) {
        this.daoPessoas = daoPessoas;
    }

    public Boolean getNovoObjeto() {
        return novoObjeto;
    }

    public void setNovoObjeto(Boolean novoObjeto) {
        this.novoObjeto = novoObjeto;
    }

}
