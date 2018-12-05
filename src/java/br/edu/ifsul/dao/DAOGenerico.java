/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author romulo
 */
public class DAOGenerico<TIPO> implements Serializable{
    
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName = "Trabalho-Final-TA-WebPU")
    protected EntityManager em;
    protected Class classePersistente;
    protected String ordem = "id";
    
    public DAOGenerico(){
    }
    
    public void persist(TIPO obj) throws Exception{
        em.persist(obj);
    }
    
    public void merge(TIPO obj) throws Exception{
        em.merge(obj);
    }
    
    @RolesAllowed("ADMINISTRADOR")
    public void remove(TIPO obj) throws Exception{
        em.merge(obj);
        em.remove(obj);
    }
    
    public TIPO getObjectById(Object id) throws Exception{
        return (TIPO) em.find(classePersistente, id);   
    }
    
    public TIPO getObjectByNomeUsuario(Object id){
        return (TIPO) em.find(classePersistente, id);
    }

    public List<TIPO> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<TIPO> getListaTodos() {
        return listaTodos;
    }

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }
    
}
