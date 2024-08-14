package com.QACK.Web.Exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException( Object id ) {
		super( "Not found. Id: " + id );
	}
	
}
