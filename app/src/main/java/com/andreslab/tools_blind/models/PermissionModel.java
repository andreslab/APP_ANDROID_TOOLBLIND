package com.andreslab.tools_blind.models;

/**
 * Created by macbookpro on 7/17/17.
 */

public class PermissionModel {
    private int id;
    private String permission_name;
    private int access;

    public PermissionModel(int id, String permission_name, int access){
        this.id = id;
        this.permission_name = permission_name;
        this.access = access;
    }

    public int getId() {
        return id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public int getAccess() {
        return access;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public void setAccess(int access) {
        this.access = access;
    }
}
