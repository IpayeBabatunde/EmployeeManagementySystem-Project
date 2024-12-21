package com.ipaye.employeemanagementsystemproject.Model;

import javax.imageio.event.IIOReadProgressListener;
import java.util.Objects;

public class Role {

    String roleName;

    String roleTitle;
    public Role(String roleName){
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty.");
        }
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || getClass() != object.getClass()) return false;
        Role role = (Role) object;
        return roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }


    public String getName() {

        return roleName;
    }

    public String getTitle() {
        return roleTitle;
    }
}
