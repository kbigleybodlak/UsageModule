
package org.openmrs.module.UsageModule;
import java.io.Serializable;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;
import java.util.Date;
import java.sql.Timestamp;

public class OrderUsage extends BaseOpenmrsObject implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer orderusage_id;
    private Integer order_id;
    private Integer user_id;
    private Integer patient_id;
    private Integer location_id;
    private Integer action_type_id;
    private Integer encounter_id;
    private Timestamp timestamp;

    public Integer getOrderusage_id() {
        return orderusage_id;
    }

    public void setOrderusage_id(Integer orderusage_id) {
        this.orderusage_id = orderusage_id;
    }
    
    public Integer getId() {
        return orderusage_id;
    }
    
    public void setId (Integer id) {
        setOrderusage_id (id);
    }
 

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Integer getAction_type_id() {
        return action_type_id;
    }

    public void setAction_type_id(Integer action_type_id) {
        this.action_type_id = action_type_id;
    }

    public Integer getEncounter_id() {
        return encounter_id;
    }

    public void setEncounter_id(Integer encounter_id) {
        this.encounter_id = encounter_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    
    
    
    
}
