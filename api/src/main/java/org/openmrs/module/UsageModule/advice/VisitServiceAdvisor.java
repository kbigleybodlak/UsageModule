
package org.openmrs.module.UsageModule.advice;
import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient; 
import org.openmrs.Visit;
import org.openmrs.api.context.Context;
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

public class VisitServiceAdvisor extends StaticMethodMatcherPointcutAdvisor 
        implements Advisor {
    
    private static final long serialVersionUID = 3333L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    @Override
    public boolean matches(Method method, Class targetClass) {
        return (method.getName().equals("saveVisit")) || 
                (method.getName().equals("voidVisit"));
    }
    
    @Override
    public Advice getAdvice() {
        return new VisitServAdvice();
    }
    
    private class VisitServAdvice implements MethodInterceptor {
        
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            
            Method thisMethod = invocation.getMethod();
            String methodName = thisMethod.getName();
            
            Object[] argsArray = invocation.getArguments();
            
            Visit visit = (Visit)argsArray[0];
            Patient patient = visit.getPatient();
            int patientNumber = -1; // temporary value
            
            try {
                patientNumber = patient.getPatientId();
            } catch (NullPointerException npe) {
                log.warn("!!! visitUsage problem: Patient Id was null");
                log.warn("!!! visitUsage proceeds with default patientid value -1");
            }
            
            Object returnedObject = null;
            
            int actionTypeNumber = 3; // "voided" is the default action type
            
            // create and start preparing VisitUsage instance
            VisitUsage visitUsage = new VisitUsage();
            User user = Context.getAuthenticatedUser();
            int userIdNumber = user.getUserId();
            visitUsage.setUser_id(new Integer(userIdNumber));
            visitUsage.setPatient_id(new Integer(patientNumber));
            int visitNumber = -1;
            
            log.warn("+++Before " + methodName + "...");
            
            if (methodName.equals("saveVisit")) {
                // before saveVisit method:
                try {                  
                    visitNumber = visit.getVisitId();
                    actionTypeNumber = 2; // visitId exists, so it's an update
                } catch (NullPointerException np) {
                    log.warn("getVisitId failed - creating a new visit...");
                    actionTypeNumber = 1; // no visitId, so it's a new visit that we are creating
                }
                
                // saveVisit Method proceeds:
                returnedObject = invocation.proceed();
                
                // after saveVisit method:
                log.warn("+++After " + methodName + "...");
                if (visitNumber<0) { // creating a new visit record
                    Visit returnedVisit = (Visit)returnedObject;   
                    visitNumber = returnedVisit.getVisitId();
                }
            } // end if saveVisit()
            else if (methodName.equals("voidVisit")) {
                                
                // voidVisit method proceeds:
                returnedObject = invocation.proceed();
                
                // after voidVisit method:
                Visit voidedVisit = (Visit)returnedObject;
                visitNumber = voidedVisit.getVisitId();
            } // end if voidVisit
            
            // complete VisitUsage instance
            visitUsage.setVisit_id(new Integer(visitNumber));
            visitUsage.setAction_type_id(new Integer(actionTypeNumber));
                
            // store VisitUsage record in database
            UsageModuleService ums = (UsageModuleService)Context.getService(UsageModuleService.class);
            ums.saveVisitUsage(visitUsage);
            
                       
            return returnedObject;
            
        } // end invoke method
        
        
    } // end private class VisitServAdvice
    
 
} // end class
