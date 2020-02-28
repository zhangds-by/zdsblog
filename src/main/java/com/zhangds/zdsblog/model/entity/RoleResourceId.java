package com.zhangds.zdsblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Create by zhangds
 * 2020-02-26 18:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResourceId implements Serializable {

    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "resource_id")
    private Long resourceId;

    public RoleResourceId(Long roleId, Long resourceId){
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RoleResourceId that = (RoleResourceId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roleId, that.roleId) && Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, resourceId);
    }
}
