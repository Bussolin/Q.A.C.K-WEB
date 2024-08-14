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

import com.QACK.Web.Model.Permission;
import com.QACK.Web.Services.PermissionService;


@RestController()
@RequestMapping(value = "/permission")
public class PermiossionController {
	
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping()
	public ResponseEntity<List<Permission>> findAll() {
		List<Permission> permissions = permissionService.findAll();
		return ResponseEntity.ok().body( permissions );
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Permission> findById(@PathVariable(value ="id")  Integer id) {
		Permission perm = permissionService.findById( id );
		return ResponseEntity.ok().body( perm );
	}
	
	@PostMapping()
	public ResponseEntity<Permission> insert(@RequestBody Permission perm ) {
		
		perm = permissionService.insert(perm);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(perm.getId()).toUri();
		
		return ResponseEntity.created( uri ).body(perm);
	}
	
	@DeleteMapping( value = "/{id}")
	@ResponseStatus( value = HttpStatus.NO_CONTENT )
	public void delete(@PathVariable(value="id") Integer id){
		permissionService.delete( id );	
	}
	
	@PutMapping( value = "/{id}" )
	public ResponseEntity<Permission> update( @PathVariable(value="id") Integer id,
											  @RequestBody Permission perm){
		perm = permissionService.update( id, perm );
		return ResponseEntity.ok().body( perm );
	}
	
}
