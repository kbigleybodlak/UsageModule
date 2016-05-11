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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.openmrs.User;
import org.openmrs.module.UsageModule.api.db.UsageModuleDAO;
import org.openmrs.module.UsageModule.PatientUsage;
import org.openmrs.module.UsageModule.OrderUsage;
import org.openmrs.module.UsageModule.VisitUsage;
import org.openmrs.module.UsageModule.ActionType;
import org.openmrs.module.UsageModule.Constants;
import org.openmrs.module.UsageModule.util.PagingInfo;

/**
 * It is a default implementation of  {@link UsageModuleDAO}.
 */
public class HibernateUsageModuleDAO implements UsageModuleDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
        protected static final String TABLE_USAGE = Constants.MODULE_ID + "_usage";
        protected static final String TABLE_PATIENTUSAGE = Constants.MODULE_ID + "_patientusage";
	protected static final String TABLE_DAILY = Constants.MODULE_ID + "_daily";
	protected static final String TABLE_ENCOUNTER = Constants.MODULE_ID + "_encounter";
        protected static final SimpleDateFormat dfSQL = new SimpleDateFormat("yyyy-MM-dd");

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
    
    public List<PatientUsage> getPatientUsages (User user, Patient patient, Date from, Date until, ActionType filter, PagingInfo paging){
        StringBuffer sb = new StringBuffer();
		sb.append("SELECT SQL_CALC_FOUND_ROWS {s.*} ");
		sb.append("FROM " + TABLE_PATIENTUSAGE + " s ");
		sb.append("WHERE 1=1 ");
		
		if (user != null)
			sb.append("  AND s.user_id = " + user.getUserId() + " ");
		if (patient != null)
			sb.append("  AND s.patient_id = " + patient.getPatientId() + " ");
		if (from != null)
			sb.append("  AND s.timestamp > '" + dfSQL.format(from) + "' ");
		if (until != null)
			sb.append("  AND s.timestamp < '" + dfSQL.format(until) + "' ");
		
		if (filter.getAction_type_id() == 1)  //creating
			sb.append("  AND s.actiontype_id = 1 ");
		/*else if (filter == ActionCriteria.ENCOUNTER)
			sb.append("  AND s.usage_id IN (SELECT usage_id FROM " + TABLE_ENCOUNTER + ") ");
                        */
		else if (filter.getAction_type_id() == 2) //updating
			sb.append("  AND s.actiontype_id = 2 ");
		else if (filter.getAction_type_id() == 3) //voiding
			sb.append("  AND s.actiontype_id = 3 ");
		
		sb.append("ORDER BY s.timestamp DESC ");
		
		sb.append("LIMIT " + paging.getPageOffset() + ", " + paging.getPageSize() + ";");
		
		Session session = sessionFactory.getCurrentSession();
		
		List<PatientUsage> results = sessionFactory.getCurrentSession().createSQLQuery(sb.toString())
			.addEntity("s", PatientUsage.class)
			.list();
		
		int count = ((Number)session.createSQLQuery("SELECT FOUND_ROWS();").uniqueResult()).intValue();
		paging.setResultsTotal(count);
		return results;
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