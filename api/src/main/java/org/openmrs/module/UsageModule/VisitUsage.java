
package org.openmrs.module.UsageModule;

import java.io.Serializable;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;
import java.util.Date;
import java.sql.Timestamp;

public class VisitUsage extends BaseOpenmrsObject implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer visitusage_id;
    private Integer visit_id;
    private Integer user_id;
    private Integer patient_id;
    //private Integer location_id;
    private Integer action_type_id;
    private Integer encounter_id;
    private Timestamp timestamp;

    public Integer getVisitusage_id() {
        return visitusage_id;
    }

    public void setVisitusage_id(Integer visitusage_id) {
        this.visitusage_id = visitusage_id;
    }
    
    public Integer getId() {
        return visitusage_id;
    }
    
    public void setId (Integer id) {
        setVisitusage_id (id);
    }
  

    public Integer getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(Integer visit_id) {
        this.visit_id = visit_id;
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
/*
    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }
*/
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
    
    
    
    
}// end class
