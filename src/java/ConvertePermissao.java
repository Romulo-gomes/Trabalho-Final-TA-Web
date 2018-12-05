
import br.edu.ifsul.modelo.Permissao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author romulo
 */
@FacesConverter(value = "convertePermissao")
public class ConvertePermissao implements Serializable, Converter{
    
    @PersistenceContext(unitName = "Trabalho-Final-TA-WebPU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string){
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Permissao.class, string);
    }
    public EntityManager getEm() {
        return em;
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o){
        if(o == null){
            return null;
        }
        Permissao obj = (Permissao) o;
        return obj.getNome();
    }

}
