/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.UsageModule.api;

import java.util.Date;
import java.util.List;
import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.UsageModule.PatientUsage;
import org.openmrs.module.UsageModule.OrderUsage;
import org.openmrs.module.UsageModule.VisitUsage;
import org.openmrs.module.UsageModule.ActionType;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(${module-name-no-spaces}Service.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface UsageModuleService extends OpenmrsService {
     
    public PatientUsage savePatientUsage(PatientUsage patientUsage);
    
    public PatientUsage getPatientUsage (Integer id);
    
    public OrderUsage saveOrderUsage(OrderUsage orderUsage);
    
    public OrderUsage getOrderUsage (Integer id);
    
    public VisitUsage saveVisitUsage(VisitUsage visitUsage);
    
    public VisitUsage getVisitUsage (Integer id);
    
    public ActionType saveActionType (ActionType actionType);
    
    public ActionType getActionType (Integer id);
    
    //More services about patient usage
    public List<PatientUsage> getPatientUsages(Date start, Date until, int usages);
    public List<OrderUsage> getOrderUsages(Date start, Date until, int usages);
    public List<VisitUsage> getVisitUsages(Date start, Date until, int usages);
    public int getPatientUsageCount();
    public int getVisitUsageCount(); 
    public int getOrderUsageCount();
} // end interface