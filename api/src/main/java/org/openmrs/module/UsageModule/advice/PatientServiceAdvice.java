
package org.openmrs.module.UsageModule.advice;

import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.springframework.aop.AfterReturningAdvice;
import org.openmrs.module.UsageModule.*;
import org.openmrs.module.UsageModule.api.*;
import org.openmrs.User;
import org.openmrs.Location;
import org.openmrs.api.context.UserContext;

public class PatientServiceAdvice implements AfterReturningAdvice {
    
    private final Log log = LogFactory.getLog(this.getClass());
    private static int count = 0;    
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, 
            Object target) throws Throwable {
        if (method.getName().equals("savePatient")) {
            log.warn("*****\nJUST REACHED AOP PatientService\n*****");   
            
            User user = Context.getAuthenticatedUser();
            int u = user.getUserId();
            int n = Context.getUserContext().getAuthenticatedUser().getUserId();
            int locationId;
            System.out.println("Once again, user number is: "+n);
            Location ll = (Location)Context.getUserContext().getLocation();
            if (ll==null) {
                locationId=5;
                System.out.println("The location returned was NULL");
            }
            else locationId =ll.getLocationId();
            //System.out.println("the location id is: "+ll.getLocationId());
            
            
           
            PatientUsage patientUsage = new PatientUsage();
            Patient patient = (Patient)args[0];           
            int p = patient.getPatientId();
            //patientUsage.setPatientusage_id(patient.getPatientId());
            System.out.println("args0 is "+(p));
            System.out.println("user is: "+u);
            //System.out.println("locale is: "+loc);
            //patientUsage.setId(5);
            patientUsage.setPatient_id(new Integer(p));
            patientUsage.setUser_id(new Integer(u));
            patientUsage.setLocation_id(new Integer(locationId));
            patientUsage.setAction_type_id(new Integer(1));
            
            UsageModuleService ums = (UsageModuleService)Context.getService(UsageModuleService.class);
            ums.savePatientUsage(patientUsage);
            
        }// end if method is "savePatient"
        
    }
    
    
    
}// end class
