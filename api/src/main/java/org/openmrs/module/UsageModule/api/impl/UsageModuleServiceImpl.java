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
import org.openmrs.User;
import org.openmrs.Visit;
import org.openmrs.module.UsageModule.PatientUsage;
import org.openmrs.module.UsageModule.OrderUsage;
import org.openmrs.module.UsageModule.VisitUsage;
import org.openmrs.module.UsageModule.ActionType;
import org.openmrs.module.UsageModule.util.PagingInfo;

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
    public List<PatientUsage> getPatientUsages (User user, Patient patient, 
            Date from, Date until, ActionType filter, PagingInfo paging){
        return dao.getPatientUsages(user,patient,from,until,filter,paging);
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
    
    
}