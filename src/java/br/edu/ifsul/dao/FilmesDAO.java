/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Filmes;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author romulo
 */
@Stateful
public class FilmesDAO<TIPO> extends DAOGenerico<Filmes> implements Serializable {
    
     public FilmesDAO(){
        super();
        classePersistente = Filmes.class;
        //ordem = "id";
    }
    
}