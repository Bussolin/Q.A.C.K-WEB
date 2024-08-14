package com.QACK.Web.util;

import java.util.List;

import com.QACK.Web.Model.DataChecker;

public abstract class QACKUtil implements DataChecker{
	
	public void verifyData() {
		if( !verifyDataContent( attributesToList() ) ) {			
			throw new IllegalArgumentException("Illegal arguments");
		}
	}
	
	private boolean verifyDataContent (List<Object> objs) {
		if( objs == null ) {
			return false;
		}
		for( Object obj : objs) {
			if( obj == null ) {
				return false;
			}
			if( obj instanceof String ) {
				if( ((String) obj).isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}
