package com.QACK.Web.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.QACK.Web.Exception.DataBaseException;
import com.QACK.Web.Exception.ResourceNotFoundException;
import com.QACK.Web.Model.Permission;
import com.QACK.Web.Model.Role;
import com.QACK.Web.Model.DTO.RoleDTO;
import com.QACK.Web.repositories.PermissionRepository;
import com.QACK.Web.repositories.RoleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private PermissionRepository permissionRepository;
	
	public List<RoleDTO> findAll(){
		
		List<RoleDTO> roles = new ArrayList<>();
		for( Role role : roleRepository.findAll() ) {
			roles.add( role.toRoleDto() );
		}
		
		return roles;
	}
	
	public Role findById( Integer id ) {
		return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Role") );
	}
	
	public Role insert(String roleName, Integer permissionId ) {
		
		Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException(permissionId, "Permission"));

        Role role = new Role();
        role.setName(roleName);
        role.setPermission( permission );
		role.verifyData();
		return roleRepository.save( role );
	}
	
	public void delete(Integer id) {
		try {
			roleRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id, "Role");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Role update( Integer id, RoleDTO roleDTO) {
		try {
			Permission permission = permissionRepository.findById(roleDTO.getPermissionId())
	                .orElseThrow(() -> new ResourceNotFoundException(roleDTO.getPermissionId(), "Permission"));
			
			Role obj = roleRepository.getReferenceById( id );
			obj.updateData( new Role( null, roleDTO.getName(), permission ) );
			
			return roleRepository.save( obj );
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id, "Role");
		}
	}
	
}
