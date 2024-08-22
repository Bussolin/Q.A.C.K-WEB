package com.QACK.Web.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.QACK.Web.Model.Role;
import com.QACK.Web.Model.DTO.RoleDTO;
import com.QACK.Web.Services.RoleService;


@RestController()
@RequestMapping(value = "/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping()
	public ResponseEntity<List<RoleDTO>> findAll() {
		List<RoleDTO> roles = roleService.findAll();
		return ResponseEntity.ok().body( roles );
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RoleDTO> findById(@PathVariable  Integer id) {
		Role role = roleService.findById( id );
		return ResponseEntity.ok().body( role.toRoleDto() );
	}
	
	@PostMapping()
	public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO roleDTO ) {
		
		Role role = roleService.insert( roleDTO.getName(), roleDTO.getPermissionId() );
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(role.getId()).toUri();
		
		return ResponseEntity.created( uri ).body(role.toRoleDto());
	}
	
	@DeleteMapping( value = "/{id}")
	@ResponseStatus( value = HttpStatus.NO_CONTENT )
	public void delete(@PathVariable Integer id){
		roleService.delete( id );	
	}
	
	@PutMapping( value = "/{id}" )
	public ResponseEntity<RoleDTO> update( @PathVariable Integer id,
											  @RequestBody RoleDTO roleDTO){
		Role role = roleService.update( id, roleDTO );
		return ResponseEntity.ok().body( role.toRoleDto() );
	}
	
}
