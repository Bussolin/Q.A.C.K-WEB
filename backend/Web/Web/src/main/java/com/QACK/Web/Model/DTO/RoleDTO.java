package com.QACK.Web.Model.DTO;

public class RoleDTO {
	
	private String name;
	private Integer permissionId;
	
	public RoleDTO() {
	}

	public RoleDTO(String name, Integer permissionId) {
		this.name = name;
		this.permissionId = permissionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
}
