package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum RoleEnum {

    CLIENT(1, "Client"),
    MASTER_ADMIN(2, "Master Admin"),
    ADMIN(3, "Admin");


    private final int roleId;
    private final String roleName;

    RoleEnum(int roleId,String roleName) {
        this.roleId=roleId;
        this.roleName=roleName;
    }

}
