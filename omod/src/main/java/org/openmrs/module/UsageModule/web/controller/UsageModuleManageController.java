/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.UsageModule.web.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.UsageModule.PatientUsage;
import org.openmrs.module.UsageModule.api.UsageModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The main controller.
 */
@Controller
public class  UsageModuleManageController {
    
    protected final Log log = LogFactory.getLog(getClass());
    
    @RequestMapping(value = "/module/UsageModule/manage", method = RequestMethod.GET)
    public void manage(ModelMap model) throws ParseException {//This controller manages the first page of the module
        //Get the current authenticated user
        User user = Context.getAuthenticatedUser();
        //Get the user id
        int user_id = user.getId();
        System.out.println("User number is "+ user_id);
        //Create a new PatientUsage object
        PatientUsage p = new PatientUsage();
        //populate entries in patient_usage
        p.setPatient_id(new Integer(7));
        p.setUser_id(new Integer(user_id));
        p.setAction_type_id(new Integer(1));
        p.setTimestamp(new Timestamp(new Date().getTime()));
        p.setEncounter_id(new Integer(1));
        //save the new patient usage to the database
        UsageModuleService ums = (UsageModuleService)Context.getService(UsageModuleService.class);
        ums.savePatientUsage(p);
        System.out.println("Saved 'p' object into 'Patientusage' database table");
        
        //get a PatientUsage object from database, accordinding to the patientusage id
        PatientUsage a_patient_usage = Context.getService(UsageModuleService.class).getPatientUsage(new Integer(1));
        
        //get a list of PatientUsage objects from database, according to year, month and day
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2016-5-14");
        Date until = sdf.parse("2016-5-15");
        //List<PatientUsage> patientUsages_1 = Context.getService(UsageModuleService.class).getPatientUsages(start, until, 2);
        String patientUsages_1 = Context.getService(UsageModuleService.class).getPatientUsages_Json(start, until, 2);
        //get a list of PatientUsage objects from database, according to year, month, day, hour and minute
        SimpleDateFormat sdf_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        start = sdf_2.parse("2016-5-12 00:36");
        until = sdf_2.parse("2016-5-15 14:29");
        System.out.println("start time: " + sdf_2.format(start));
        System.out.println("until time: " + sdf_2.format(until));
        
        //List<PatientUsage> patientUsages_2 = Context.getService(UsageModuleService.class).getPatientUsages(start, until, 2);
        String patientUsages_2 = Context.getService(UsageModuleService.class).getPatientUsages_Json(start, until, 2);

        //System.out.println("Patient usages between 2016-5-12 00:36 and 2016-5-15 14:29 are: ");
        //for(PatientUsage pu: patientUsages_2){
          //  System.out.println(pu.getInfo());
        //}
        
        //Context.getService(UsageModuleService.class).getPatientUsageCount();
        System.out.println("Get total number of Patient usages: " +
                           Context.getService(UsageModuleService.class).getPatientUsageCount());
        
        
        model.addAttribute("user", Context.getAuthenticatedUser());
        model.addAttribute("new_patient_info", p.getInfo());
        model.addAttribute("one_patient_usage_from_database", a_patient_usage.getInfo());
        model.addAttribute("patient_usages", patientUsages_1);
        model.addAttribute("patient_usages_2", patientUsages_2);
    }

}