package com.QACK.Web.Model;

import java.io.Serializable;
import java.util.List;

import com.QACK.Web.Model.DTO.RoleDTO;
import com.QACK.Web.util.QACKUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Role")
public class Role extends QACKUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

	public Role() {}

	public Role(Integer id, String name, Permission permission) {
		this.id = id;
		this.name = name;
		this.permission = permission;
	}

	public void updateData( Role obj ) {
		this.name = obj.getName();
		this.permission = obj.getPermission();
	}
	
	public RoleDTO toRoleDto() {
		return new RoleDTO( this.getName(), this.getPermission().getId() );
	}
	
	@Override
	public List<Object> attributesToList() {
		return List.of( this.name, this.permission );
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
}
