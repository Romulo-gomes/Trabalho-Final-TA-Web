/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoas;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author romulo
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    
    private Pessoas pessoaAutenticado;
    @EJB
    private PessoaDAO<Pessoas> dao;
    private String loginn;
    private String senha;
    
    public ControleLogin(){
        
    }

    public String irPaginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        try {
            HttpServletRequest request = (HttpServletRequest)
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            
            //realiza o login
            request.login(this.loginn, this.senha);
            System.out.println("login : " + this.loginn);
            if(request.getUserPrincipal() != null){
                pessoaAutenticado = dao.localizaPorNomeUsuario(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login realizado com sucesso");
                loginn = "";
                senha = "";
            }
            return "/index?faces-redirect=true";
        } catch (Exception e) {
            Util.mensagemErro("Login ou senha inválidos! chegou aqui"+ Util.getMensagemErro(e));
            return "/login?faces-redirect=true";
            //  return "/index?faces-redirect=true";
        }
    }
    
    public String efetuarLogout(){
        try {
            pessoaAutenticado = null;
            HttpServletRequest request = (HttpServletRequest)
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // realiza o Logout
            request.logout();
            Util.mensagemInformacao("Logout efetuado com sucesso!");
            return "/index?faces-redirect=true";
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao efetuar logout! "+ Util.getMensagemErro(e));
            return "/index?faces-redirect=true";
        }
    }
    
    
    public Pessoas getPessoaAutenticado() {
        return pessoaAutenticado;
    }

    public void setPessoaAutenticado(Pessoas pessoaAutenticado) {
        this.pessoaAutenticado = pessoaAutenticado;
    }

    public PessoaDAO<Pessoas> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoas> dao) {
        this.dao = dao;
    }

    public String getLogin() {
        return loginn;
    }

    public void setLogin(String login) {
        this.loginn = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
