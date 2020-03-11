package com.zhangds.zdsblog.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;

/**
 * Create by zhangds
 * 2020-02-26 18:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuId implements Serializable {

    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "menu_id")
    private Long menuId;

    public RoleMenuId(Long roleId, Long menuId){
        this.roleId = roleId;
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RoleMenuId that = (RoleMenuId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roleId, that.roleId) && Objects.equals(menuId, that.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, menuId);
    }
}
