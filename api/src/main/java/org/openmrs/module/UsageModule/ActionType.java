
package org.openmrs.module.UsageModule;
import java.io.Serializable;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;
import java.util.Date;

public class ActionType extends BaseOpenmrsObject implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    private Integer action_type_id;
    private String name;
    private String description;

    public Integer getAction_type_id() {
        return action_type_id;
    }

    public void setAction_type_id(Integer action_type_id) {
        this.action_type_id = action_type_id;
    }
    
    public Integer getId() {
        return action_type_id;
    }
    
    public void setId (Integer id) {
        setAction_type_id (id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
    
    
    
    
}// end class
