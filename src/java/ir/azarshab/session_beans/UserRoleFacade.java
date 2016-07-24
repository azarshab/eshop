/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.azarshab.session_beans;

import ir.azarshab.model.UserRole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author k1
 */
@Stateless
public class UserRoleFacade extends AbstractFacade<UserRole> {

    @PersistenceContext(unitName = "ESHOPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRoleFacade() {
        super(UserRole.class);
    }
    
}
