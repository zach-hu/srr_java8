/*
 * Created on July 25, 2006
 */
package com.tsa.puridiom.vendorregistration;

import com.tsagate.foundation.processengine.*;

/**
 * @author Kelli
 * 
 *  To be used only with the vendor-registration application processes
 */
public class VendorRegistrationProcessLoader extends PuridiomProcessLoader {
	public VendorRegistrationProcessLoader(String organizationId) {
		this.setOrganizationId(organizationId);
		this.setApplicationName("vendor-registration");
	}
}
