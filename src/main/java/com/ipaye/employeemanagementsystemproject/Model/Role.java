package com.ipaye.employeemanagementsystemproject.Model;

import javax.imageio.event.IIOReadProgressListener;
import java.util.Objects;

public class Role {

    String roleName;

    public Role(String roleName){
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

}
