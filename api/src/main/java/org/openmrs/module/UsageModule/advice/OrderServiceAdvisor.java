
package org.openmrs.module.UsageModule.advice;
import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient; 
import org.openmrs.PatientIdentifier;
import org.openmrs.Order;
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

public class OrderServiceAdvisor extends StaticMethodMatcherPointcutAdvisor 
        implements Advisor {
    
    private static final long serialVersionUID = 3333L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    @Override
    public boolean matches(Method method, Class targetClass) {
        // only 'run' this advice on the getter methods        
        return (method.getName().equals("saveOrder")) || 
                (method.getName().equals("voidOrder"));
    }
    
    @Override
    public Advice getAdvice() {
        return new OrderServAdvice();
    }
    
    private class OrderServAdvice implements MethodInterceptor {
        
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            
            Method thisMethod = invocation.getMethod();
            String methodName = thisMethod.getName();
            
            Object[] argsArray = invocation.getArguments();
            
            Order order = (Order)argsArray[0];
            Patient patient = order.getPatient();
            int patientNumber = -1; // temporary value
            try {
                patientNumber = patient.getPatientId();
            } catch (NullPointerException npe) {
                log.warn("!!! orderUsage problem: Patient Id was null");
                log.warn("!!! orderUsage proceeds with default patientid value -1");
            }
            
            Object returnedObject = null;
            
            int actionTypeNumber = 3; // "voided" is the default action type
            
            // create and start preparing OrderUsage instance
            OrderUsage orderUsage = new OrderUsage();
            User user = Context.getAuthenticatedUser();
            int userIdNumber = user.getUserId();
            orderUsage.setUser_id(new Integer(userIdNumber));
            orderUsage.setPatient_id(new Integer(patientNumber));
            int orderNumber = -1;
            
            log.warn("+++Before " + methodName + "...");
            
            if (methodName.equals("saveOrder")) {
                // before saveOrder method:
                try {                  
                    orderNumber = order.getOrderId();
                    actionTypeNumber = 2; // orderId exists, so it's an update
                } catch (NullPointerException np) {
                    log.warn("getOrderId failed - creating a new order...");
                    actionTypeNumber = 1; // no orderId, so it's a new order that we are creating
                }
                
                // saveOrder Method proceeds:
                returnedObject = invocation.proceed();
                
                // after saveOrder method:
                log.warn("+++After " + methodName + "...");
                if (orderNumber<0) { // creating a new order record
                    Order returnedOrder = (Order)returnedObject;   
                    orderNumber = returnedOrder.getOrderId();
                }
                
                
            } // end if saveOrder()
            else if (methodName.equals("voidOrder")) {
                                
                // voidOrder method proceeds:
                returnedObject = invocation.proceed();
                
                // after voidOrder method:
                Order voidedOrder = (Order)returnedObject;
                orderNumber = voidedOrder.getOrderId();
            } // end if voidPatient
            
            // complete OrderUsage instance
            orderUsage.setOrder_id(new Integer(orderNumber));
            orderUsage.setAction_type_id(new Integer(actionTypeNumber));
                
            // store PatientUsage record in database
            UsageModuleService ums = (UsageModuleService)Context.getService(UsageModuleService.class);
            ums.saveOrderUsage(orderUsage);
            
            
            
            return returnedObject;
            
        } // end invoke method
        
        
        
    } // end private class OrderServAdvice
    
    
    
    
} // end class
