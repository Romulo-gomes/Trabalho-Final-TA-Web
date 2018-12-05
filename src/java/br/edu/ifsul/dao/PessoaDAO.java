/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoas;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author romulo
 */
@Stateful
public class PessoaDAO<TIPO> extends DAOGenerico<Pessoas> implements Serializable{
    
    public PessoaDAO(){
        super();
        classePersistente = Pessoas.class;
    }
    
    public Boolean verificaUnicidadeNomeUsuario (String nomeUsuario) throws Exception {
        String jpql = "from Pessoas where login = :NomeUsuario";
        Query query = em.createQuery(jpql);
        query.setParameter("NomeUsuario", nomeUsuario);
        if (query.getResultList().size() >0){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public Pessoas getObjectById(Object id) throws Exception{
        Pessoas obj = em.find(Pessoas.class, id);
        // Inicializando a coleção para não dar erro de lazy inicialzation exception
        obj.getPermissoes().size();
        return obj;
    }
    
    public Pessoas localizaPorNomeUsuario(String login) {
        Pessoas obj = (Pessoas) super.getEm()
                .createQuery("from Pessoas where upper(login) = :login")
                .setParameter("login", login.toUpperCase()).getSingleResult();
        obj.getPermissoes().size();        
        return obj;
    }
}
