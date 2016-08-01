/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.azarshab.session_beans;

import ir.azarshab.enums.Roles;
import ir.azarshab.model.User;
import ir.azarshab.model.UserRole;
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

    public UserRole getRegularUserRole() {
        Query query = em.createQuery(
                "SELECT u FROM UserRole u WHERE u.roleValue = :roleValue");
        query.setParameter("roleValue", Roles.REGULAR_USER.ordinal());
        List<UserRole> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

}
