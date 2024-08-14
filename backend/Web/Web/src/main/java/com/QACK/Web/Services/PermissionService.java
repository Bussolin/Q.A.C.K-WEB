package com.QACK.Web.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.QACK.Web.Exception.DataBaseException;
import com.QACK.Web.Exception.ResourceNotFoundException;
import com.QACK.Web.Model.Permission;
import com.QACK.Web.repositories.PermissionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public List<Permission> findAll(){
		return permissionRepository.findAll();
	}
	
	public Permission findById( Integer id ) {
		Optional<Permission> perm = permissionRepository.findById(id);
		return perm.orElseThrow(() -> new ResourceNotFoundException(id) );
	}
	
	public Permission insert(Permission perm) {
		perm.verifyData();
		return permissionRepository.save( perm );			
		
	}
	
	public void delete(Integer id) {
		try {
			
			permissionRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Permission update( Integer id, Permission perm) {
		try {
			Permission obj = permissionRepository.getReferenceById( id );
			obj.updateData( perm );
			return permissionRepository.save( obj );
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException( id );
		}
	}
	
}
