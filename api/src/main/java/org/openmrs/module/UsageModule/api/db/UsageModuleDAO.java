/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.UsageModule.api.db;

import java.util.Date;
import java.util.List;
import org.openmrs.module.UsageModule.api.UsageModuleService;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.UsageModule.ActionType;
import org.openmrs.module.UsageModule.PatientUsage;
import org.openmrs.module.UsageModule.OrderUsage;
import org.openmrs.module.UsageModule.VisitUsage;
import org.openmrs.module.UsageModule.util.PagingInfo;

/**
 *  Database methods for {@link UsageModuleService}.
 */
public interface UsageModuleDAO {
	
    PatientUsage savePatientUsage(PatientUsage patientUsage);
    
    PatientUsage getPatientUsage (Integer id);
    
    public List<PatientUsage> getPatientUsages (User user, Patient patient, 
            Date from, Date until, ActionType filter, PagingInfo paging);

    
    OrderUsage saveOrderUsage(OrderUsage orderUsage);
    
    OrderUsage getOrderUsage (Integer id);
    
    VisitUsage saveVisitUsage(VisitUsage visitUsage);
    
    VisitUsage getVisitUsage(Integer id);
    
    ActionType saveActionType (ActionType actionType);
    
    ActionType getActionType (Integer id);

    
    
} // end interface