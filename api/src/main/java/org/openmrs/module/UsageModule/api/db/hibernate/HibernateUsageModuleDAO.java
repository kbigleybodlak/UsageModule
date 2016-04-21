/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.UsageModule.api.db.hibernate;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.UsageModule.api.db.UsageModuleDAO;
import org.openmrs.module.UsageModule.PatientUsage;
import org.openmrs.module.UsageModule.OrderUsage;
import org.openmrs.module.UsageModule.VisitUsage;
import org.openmrs.module.UsageModule.ActionType;

/**
 * It is a default implementation of  {@link UsageModuleDAO}.
 */
public class HibernateUsageModuleDAO implements UsageModuleDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }
    
    public PatientUsage savePatientUsage(PatientUsage patientUsage) {
        sessionFactory.getCurrentSession().save(patientUsage);
        return patientUsage;
    }
    
    public PatientUsage getPatientUsage (Integer id) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(
                PatientUsage.class);
        crit.add(Restrictions.eq("patientusage_id", id));
        return (PatientUsage) crit.uniqueResult();
    }
    
    public OrderUsage saveOrderUsage(OrderUsage orderUsage) {
        sessionFactory.getCurrentSession().save(orderUsage);
        return orderUsage;
    }
    
    public OrderUsage getOrderUsage(Integer id) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(
                OrderUsage.class);
        crit.add(Restrictions.eq("orderusage_id", id));
        return (OrderUsage)crit.uniqueResult();
    }
    
    public VisitUsage saveVisitUsage(VisitUsage visitUsage) {
        sessionFactory.getCurrentSession().save(visitUsage);
        return visitUsage;
    }
    
    public VisitUsage getVisitUsage(Integer id) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(
                VisitUsage.class);
        crit.add(Restrictions.eq("visitusage_id", id));
        return (VisitUsage)crit.uniqueResult();
    }
    
    public ActionType saveActionType(ActionType actionType) {
        sessionFactory.getCurrentSession().save(actionType);
        return actionType;
    }
    
    public ActionType getActionType(Integer id) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(
                ActionType.class);
        crit.add(Restrictions.eq("action_type_id", id));
        return (ActionType)crit.uniqueResult();
    }
    
}// end class