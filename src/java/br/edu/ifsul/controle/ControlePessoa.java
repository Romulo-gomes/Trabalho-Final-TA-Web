/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FilmesDAO;
import br.edu.ifsul.dao.PermissaoDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Filmes;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.Pessoas;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author romulo
 */
@Named(value = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable{
    
    @EJB
    private PessoaDAO<Pessoas> dao;
    private Pessoas objeto;
    private Boolean editando;
    
    @EJB
    private FilmesDAO<Filmes> daoFilmes;
    private Boolean novoObjeto;
    
    @EJB
    private PermissaoDAO<Permissao> daoPermissao;
    private Permissao permissao;
    private Boolean editandoPermissao;

    public ControlePessoa() {
        editando = false;
        editandoPermissao = false;
    }

    public void verificaUnicidadeUsuario(){
        if(novoObjeto){
            try {
                    if(!dao.verificaUnicidadeNomeUsuario(objeto.getLogin())){
                        Util.mensagemErro("Login do usuário: '" + objeto.getLogin()
                                + "' já existe no banco de dados!");
                        // capturando o componente
                        UIComponent comp =
                                UIComponent.getCurrentComponent(FacesContext.getCurrentInstance());
                        if(comp != null){
                            UIInput input = (UIInput) comp;
                            input.setValid(false);
                        }
                    }
                } catch (Exception e) {
                    Util.mensagemErro("Erro do sistema: "+ Util.getMensagemErro(e));
                }
            } 
            
        } 
    
    public String listar(){
        editando = false;
        return "/privado/pessoa/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Pessoas();
        editando = true;
        novoObjeto=true;
        editandoPermissao= false;
    }
    
    public void alterar (Object id){
        try{
            //objeto = dao.getObjectByNomeUsuario(id);
            objeto = dao.getObjectById(id);
            editando = true;
            novoObjeto=false;
            editandoPermissao=false;
        }catch(Exception ex){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(ex));
        }
    }
    
    public void novaPermissao(){
        editandoPermissao= true;
    }
    
    public void salvaPermissao(){
        if(objeto.getPermissoes().contains(permissao)){
            Util.mensagemErro("Pessoa já possui esta permissão");
        } else{
            objeto.getPermissoes().add(permissao);
            Util.mensagemInformacao("Permissão adicionada com sucesso!");
        }
        editandoPermissao = false;
    }
    
    public void removerPermissao(Permissao obj){
        objeto.getPermissoes().remove(obj);
        Util.mensagemInformacao("Permissao removida com sucesso!!");
    }
    
    public void excluir (Object id){
        try {
            //objeto = dao.getObjectByNomeUsuario(id);
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "+ Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if(novoObjeto){
                dao.persist(objeto);
                
            }else{
                dao.merge(objeto); // tem que persistir alguma coisa para inserir o nome de usuário
            }
            editando = false;
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }
    
    
    
    
    public PessoaDAO<Pessoas> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoas> dao) {
        this.dao = dao;
    }

    public Pessoas getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoas objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public FilmesDAO<Filmes> getDaoFilmes() {
        return daoFilmes;
    }

    public void setDaoFilmes(FilmesDAO<Filmes> daoFilmes) {
        this.daoFilmes = daoFilmes;
    }

    public Boolean getNovoObjeto() {
        return novoObjeto;
    }

    public void setNovoObjeto(Boolean novoObjeto) {
        this.novoObjeto = novoObjeto;
    }

    public PermissaoDAO<Permissao> getDaoPermissao() {
        return daoPermissao;
    }

    public void setDaoPermissao(PermissaoDAO<Permissao> daoPermissao) {
        this.daoPermissao = daoPermissao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Boolean getEditandoPermissao() {
        return editandoPermissao;
    }

    public void setEditandoPermissao(Boolean editandoPermissao) {
        this.editandoPermissao = editandoPermissao;
    }
    
    
    
}
