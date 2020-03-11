package com.zhangds.zdsblog.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Create by zhangds
 * 2020-02-26 18:00
 **/
@Data

//@Embeddable
@NoArgsConstructor
public class UserRoleId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long roleId;

    public UserRoleId(Long userId, Long roleId){
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UserRoleId that = (UserRoleId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roleId, that.roleId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, userId);
    }
}
