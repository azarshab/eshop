/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.azarshab.session_beans;

import ir.azarshab.model.User;
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
public class UserFacade extends AbstractFacade<User> {
    
    @PersistenceContext(unitName = "ESHOPPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserFacade() {
        super(User.class);
    }
    
    public boolean login(String username, String password) {
        Query query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username and u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return false;
        }
        return true;
    }
    
    public User getUserByUsername(String username) {
        Query query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

   
}
