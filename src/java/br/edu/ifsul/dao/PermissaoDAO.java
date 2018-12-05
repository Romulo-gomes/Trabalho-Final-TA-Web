/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.dao.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author romulo
 */
@Stateful
public class PermissaoDAO<TIPO> extends DAOGenerico<Permissao> implements Serializable{

    
    
    public PermissaoDAO(){
        super();
        classePersistente = Permissao.class;
    }
}