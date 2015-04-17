/*
 * Created on March 11, 2004
 */
package com.tsa.puridiom.supplierportal;

import com.tsagate.foundation.processengine.*;
import java.util.*;


/**
 * @author Kelli
 */
public class PuridiomSupplierPortalProcessLoader extends PuridiomProcessLoader {
	public PuridiomSupplierPortalProcessLoader(String organizationId) {
		this.setOrganizationId(organizationId);
		this.setApplicationName("supplierportal");
	}
/*	protected String getProcessPath() {
		ResourceBundle resources = ResourceBundle.getBundle("tsagate"); 
		String path = resources.getString("bidboard-process-xml-path");
		
		return path;
	}
*/
}
