/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page.controller;

import java.util.Date;
import java.util.List;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.UsageModule.ActionType;
import org.openmrs.module.UsageModule.PatientUsage;
import org.openmrs.module.UsageModule.api.UsageModuleService;
import org.openmrs.module.UsageModule.util.PagingInfo;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author karl
 */
public class UsageModuleHomePageController {
    
    public void controller(@RequestParam(value = "userid", required = false) int user_id,
            @RequestParam(value = "patient", required = false) Patient patient,
            @RequestParam(value = "from") Date from,
            @RequestParam(value = "until") Date until,
            @RequestParam(value= "filter", required = false) ActionType filter,
            @RequestParam(value = "paging", required = false) PagingInfo paging
            ){
    /*List<PatientUsage> patientUsages = Context.getService(UsageModuleService.class)
            .getPatientUsages(User user, Patient patient, Date from, Date until, ActionType filter, PagingInfo paging);*/
    }
    
}
