
package org.openmrs.module.UsageModule.advice;

import java.lang.reflect.Method; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

public class OrderServiceAdvice implements AfterReturningAdvice {
    
    private Log log = LogFactory.getLog(this.getClass());
    private static int count = 0;  
    public void afterReturning(Object returnValue, Method method, Object[] args, 
            Object target) throws Throwable {
        if (method.getName().equals("saveOrder")) {    
            log.warn("JUST REACHED AOP for OrderService");
            log.warn("Method: " + method.getName() + ". After advice called " 
                    + (++count) + " time(s) now.");
        }
    }
    
    
    
    
} // end class

