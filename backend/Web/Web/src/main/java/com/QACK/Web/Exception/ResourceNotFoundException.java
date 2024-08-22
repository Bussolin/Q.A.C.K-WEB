package com.QACK.Web.Exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException( Object id, String entity ) {
		super( "Not found. " + entity + "  Id: " + id );
	}
	
}
