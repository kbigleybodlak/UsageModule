
package org.openmrs.module.UsageModule.advice;

import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient; import org.openmrs.PatientIdentifier;
import org.openmrs.api.context.Context;
import org.springframework.aop.MethodBeforeAdvice;
import org.openmrs.module.UsageModule.*;
import org.openmrs.module.UsageModule.api.*;
import org.openmrs.User;
import org.openmrs.Location;
import org.openmrs.api.context.UserContext;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

public class PatientServiceAdvisor extends StaticMethodMatcherPointcutAdvisor 
        implements Advisor {
    
    private static final long serialVersionUID = 3333L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    @Override
    public boolean matches(Method method, Class targetClass) {
        // only 'run' this advice on the getter methods        
        return (method.getName().equals("savePatient")) || 
                (method.getName().equals("voidPatient"));
    }
    
    @Override
    public Advice getAdvice() {
        return new PatientServAdvice();
    }
    
    private class PatientServAdvice implements MethodInterceptor {
        
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Method thisMethod = invocation.getMethod();
            String methodName = thisMethod.getName();
            
            Object[] argsArray = invocation.getArguments();
            
            Patient patient = (Patient)argsArray[0];
            
            Object returnedObject = null;
            int patientNumber = -1;
            int actionTypeNumber = 3; // voiding by default; changed in savePatient block
            
            // create and start preparing PatientUsage instance
            PatientUsage patientUsage = new PatientUsage();
            User user = Context.getAuthenticatedUser();
            int userIdNumber = user.getUserId();
            patientUsage.setUser_id(new Integer(userIdNumber));
            
            
            log.warn("+++Before " + methodName + "...");
            
            if (methodName.equals("savePatient")) {
                // before savePatient method:
                try {                  
                    patientNumber = patient.getPatientId();
                    actionTypeNumber = 2; // patientId exists, so it's an update
                } catch (NullPointerException np) {
                    log.warn("getPatientId failed - creating a new patient...");
                    actionTypeNumber = 1; // no patientId, so it's a new patient
                }
                
                // savePatient Method proceeds:
                returnedObject = invocation.proceed();
                
                // after savePatient method:
                log.warn("+++After " + methodName + "...");
                if (patientNumber<0) { // creating a new patient record
                    Patient returnedPatient = (Patient)returnedObject;   
                    patientNumber = returnedPatient.getPatientId();
                }
                else { // updating an existing patient record
                }
                
            } // end if savePatient()
            else if (methodName.equals("voidPatient")) {
                                
                // voidPatient method proceeds:
                returnedObject = invocation.proceed();
                
                // after voidPatient method:
                Patient voidedPatient = (Patient)returnedObject;
                patientNumber = voidedPatient.getPatientId();
            } // end if voidPatient
            
            // complete PatientUsage instance
            patientUsage.setPatient_id(new Integer(patientNumber));
            patientUsage.setAction_type_id(new Integer(actionTypeNumber));
                
            // store PatientUsage record in database
            UsageModuleService ums = (UsageModuleService)Context.getService(UsageModuleService.class);
            ums.savePatientUsage(patientUsage);
            
            return returnedObject;
        }
    } // end private class PatientServAdvice
    
    
    
    
} // end class
