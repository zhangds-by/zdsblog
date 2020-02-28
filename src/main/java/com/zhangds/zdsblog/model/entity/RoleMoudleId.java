package com.zhangds.zdsblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Create by zhangds
 * 2020-02-26 18:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMoudleId implements Serializable {

    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "moudle_id")
    private Long moudleId;

    public RoleMoudleId(Long roleId, Long moudleId){
        this.roleId = roleId;
        this.moudleId = moudleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RoleMoudleId that = (RoleMoudleId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roleId, that.roleId) && Objects.equals(moudleId, that.moudleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, moudleId);
    }
}
