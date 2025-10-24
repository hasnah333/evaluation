package classes;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmployeTacheId implements Serializable {
    private Long employeId;
    private Long tacheId;
    
    public EmployeTacheId() {}
    
    public EmployeTacheId(Long employeId, Long tacheId) { 
        this.employeId = employeId; 
        this.tacheId = tacheId; 
    }
    
    public Long getEmployeId() { return employeId; }
    public Long getTacheId() { return tacheId; }
    public void setEmployeId(Long employeId) { this.employeId = employeId; }
    public void setTacheId(Long tacheId) { this.tacheId = tacheId; }
    
    @Override 
    public boolean equals(Object o) { 
        if (this == o) return true; 
        if (!(o instanceof EmployeTacheId)) return false; 
        EmployeTacheId that = (EmployeTacheId) o; 
        return java.util.Objects.equals(employeId, that.employeId) && 
               java.util.Objects.equals(tacheId, that.tacheId); 
    }
    
    @Override 
    public int hashCode() { 
        return java.util.Objects.hash(employeId, tacheId); 
    }
}
