/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.azarshab.session_beans;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import ir.azarshab.model.Pictures;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author k1
 */
@Stateless
public class PicturesFacade extends AbstractFacade<Pictures> {

    @PersistenceContext(unitName = "ESHOPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PicturesFacade() {
        super(Pictures.class);
    }

//    public List<Pictures> getPicturesByCatValue(Integer catValue) {
//        Query query = em.createQuery(
//                "SELECT p FROM Pictures p INNER JOIN p.categoryList c WHERE c.catValue= :catValue");
//        query.setParameter("catValue", catValue);
//        List<Pictures> results = query.getResultList();
//        return results;
//    }

    public List<String> getPicturesByCatValue(Integer catValue) {
        Query query = em.createQuery(
                "SELECT p.name FROM Pictures p INNER JOIN p.categoryList c WHERE c.catValue= :catValue");
        query.setParameter("catValue", catValue);
        List<String> results = query.getResultList();
        return results;
    }
}
