package com.QACK.Web.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QACK.Web.Exception.ResourceNotFoundException;
import com.QACK.Web.Model.Role;
import com.QACK.Web.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll(){
		return roleRepository.findAll();
	}
	
	public Role findById( Integer id ) {
		Optional<Role> role = roleRepository.findById(id);
		return role.orElseThrow(() -> new ResourceNotFoundException(id) );
	}
	
	public Role insert(Role role) {
		return roleRepository.save( role );
	}
	
	public void delete(Integer id) {
		roleRepository.deleteById(id);
	}
	
	public Role update( Integer id, Role role) {
		Role obj = roleRepository.getReferenceById( id );
		obj.updateData( role );
		return roleRepository.save( obj );
	}
	
}
