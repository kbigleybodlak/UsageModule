/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.UsageModule.api.impl;


import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.UsageModule.api.UsageModuleService;
import org.openmrs.module.UsageModule.api.db.UsageModuleDAO;
import org.openmrs.Patient;
import org.openmrs.Order;
import org.openmrs.Visit;
import org.openmrs.module.UsageModule.PatientUsage;
import org.openmrs.module.UsageModule.OrderUsage;
import org.openmrs.module.UsageModule.VisitUsage;
import org.openmrs.module.UsageModule.ActionType;

/**
 * It is a default implementation of {@link UsageModuleService}.
 */
public class UsageModuleServiceImpl extends BaseOpenmrsService implements UsageModuleService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private UsageModuleDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(UsageModuleDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public UsageModuleDAO getDao() {
	    return dao;
    }
    
    public Log getLog() {
        return log;
    }
    
    @Transactional
    public PatientUsage savePatientUsage(PatientUsage patientUsage) {
        return dao.savePatientUsage(patientUsage);
    }
    
    @Transactional(readOnly = true)
    public PatientUsage getPatientUsage (Integer id) {
        return dao.getPatientUsage(id);
    }
    
    @Transactional
    public OrderUsage saveOrderUsage(OrderUsage orderUsage) {
        return dao.saveOrderUsage(orderUsage);
    }
    
    @Transactional(readOnly = true)
    public OrderUsage getOrderUsage (Integer id) {
        return dao.getOrderUsage(id);
    }
    
    @Transactional
    public VisitUsage saveVisitUsage(VisitUsage visitUsage) {
        return dao.saveVisitUsage(visitUsage);
    }
    
    @Transactional(readOnly = true)
    public VisitUsage getVisitUsage (Integer id) {
        return dao.getVisitUsage(id);
    }
    
    @Transactional
    public ActionType saveActionType(ActionType actionType) {
        return dao.saveActionType(actionType);
    }
    
    @Transactional (readOnly = true)
    public ActionType getActionType (Integer id) {
        return dao.getActionType(id);
    }

    @Override
    public List<PatientUsage> getPatientUsages(Date start, Date until, int usages) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //get the total number of patientUsages in database
        int total = this.getPatientUsageCount();
        List<PatientUsage> results = dao.getPatientUsages(start, until);
        
        //apply logic here to determine the scope of results to be returned
        if(usages < total){
            //need to be implemented
        }else{
            //need to be implemented
        }
                
        return results;
    }

    @Override
    public List<OrderUsage> getOrderUsages(Date start, Date until, int usages) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int total = this.getOrderUsageCount();
        List<OrderUsage> results = dao.getOrderUsages(start, until);
        
        //apply logic here to determine the scope of results to be returned
        if(usages < total){
            //need to be implemented
        }else{
            //need to be implemented
        }
                
        return results;
    }

    @Override
    public List<VisitUsage> getVisitUsages(Date start, Date until, int usages) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int total = this.getVisitUsageCount();
        List<VisitUsage> results = dao.getVisitUsages(start, until);
        
        //apply logic here to determine the scope of results to be returned
        if(usages < total){
            //need to be implemented
        }else{
            //need to be implemented
        }
                
        return results;
    }

    @Override
    public int getPatientUsageCount() {
        return dao.getPatientUsageCount();
    }

    @Override
    public int getVisitUsageCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return dao.getVisitUsageCount();
    }

    @Override
    public int getOrderUsageCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return dao.getOrderUsageCount();
    }
    
    
    
    
}