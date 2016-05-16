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
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
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
        protected static final SimpleDateFormat dfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
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
    //Find a patient usage through patient usage id
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

    @Override
    public List<PatientUsage> getPatientUsages(Date start, Date until) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //get the current Session object
        Session session = sessionFactory.getCurrentSession();
        //creat a hibernate query
        StringBuffer sb = new StringBuffer();
        //sb.append("SELECT P.user_id, P.timestamp"
        sb.append(" FROM org.openmrs.module.UsageModule.PatientUsage AS P");
        sb.append(" Where P.timestamp > '" + dfSQL.format(start) +"' ");
        sb.append(" AND P.timestamp < '" + dfSQL.format(until) + "'");
        sb.append(" ORDER BY P.timestamp DESC ");
        //sb.append(" GROUP BY P.user_id ");
        String hql = sb.toString();
        Query query = session.createQuery(hql);
        List<PatientUsage> results = query.list();
        //for testing if query return a list of result
        System.out.println("----------Start of Hibernate query output---------");
        for(PatientUsage pu : results){
                   System.out.println(pu.getInfo());
        }
        System.out.println("----------End of Hibernate query output-----------");
        return results;
    }

    @Override
    public List<VisitUsage> getVisitUsages(Date start, Date until) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //get the current Session object
        Session session = sessionFactory.getCurrentSession();
        //creat a hibernate query
        StringBuffer sb = new StringBuffer();
        //sb.append("SELECT P.user_id, P.timestamp"
        sb.append(" FROM org.openmrs.module.UsageModule.VisitUsage AS V");
        sb.append(" Where V.timestamp > '" + dfSQL.format(start) +"' ");
        sb.append(" AND V.timestamp < '" + dfSQL.format(until) + "'");
        sb.append(" ORDER BY P.timestamp DESC ");
        //sb.append(" GROUP BY P.user_id ");
        String hql = sb.toString();
        Query query = session.createQuery(hql);
        List<VisitUsage> results = query.list();
       
        return results;
    }

    @Override
    public List<OrderUsage> getOrderUsages(Date start, Date until) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //get the current Session object
        Session session = sessionFactory.getCurrentSession();
        //creat a hibernate query
        StringBuffer sb = new StringBuffer();
        //sb.append("SELECT P.user_id, P.timestamp"
        sb.append(" FROM org.openmrs.module.UsageModule.OrderUsage AS O");
        sb.append(" Where O.timestamp > '" + dfSQL.format(start) +"' ");
        sb.append(" AND O.timestamp < '" + dfSQL.format(until) + "'");
        sb.append(" ORDER BY O.timestamp DESC ");
        //sb.append(" GROUP BY P.user_id ");
        String hql = sb.toString();
        Query query = session.createQuery(hql);
        List<OrderUsage> results = query.list();
       
        return results;
    }
    

    @Override
    public int getPatientUsageCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.getCurrentSession();
        //StringBuffer sb = new StringBuffer();
        //sb.append("SELECT count(distinct P.patientusage_id) ");
        //sb.append("FROM org.openmrs.module.UsageModule.PatientUsage AS P");
        //Query query = session.createQuery(sb.toString());
        Criteria cr = session.createCriteria(org.openmrs.module.UsageModule.PatientUsage.class);
        cr.setProjection(Projections.rowCount());
        List rowCount = cr.list();
        System.out.println("--------HIBERNATE----ROW COUNT-----");
        System.out.println("Total Coint: " + rowCount.get(0) );
        return ((Long)rowCount.get(0)).intValue();
        
    }

    @Override
    public int getVisitUsageCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(org.openmrs.module.UsageModule.VisitUsage.class);
        cr.setProjection(Projections.rowCount());
        List rowCount = cr.list();
        System.out.println("--------HIBERNATE----ROW COUNT-----");
        System.out.println("Total Coint: " + rowCount.get(0) );
        return ((Long)rowCount.get(0)).intValue();
    }

    @Override
    public int getOrderUsageCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(org.openmrs.module.UsageModule.OrderUsage.class);
        cr.setProjection(Projections.rowCount());
        List rowCount = cr.list();
        System.out.println("--------HIBERNATE----ROW COUNT-----");
        System.out.println("Total Coint: " + rowCount.get(0) );
        return ((Long)rowCount.get(0)).intValue();
    }
    
}// end class