/*
 * Created on July 25, 2006
 */
package com.tsa.puridiom.vendorregistration;

import com.tsagate.foundation.processengine.*;

/**
 * @author Kelli
 */
public class InvokeVendorRegistrationProcessTask extends InvokePuridiomProcessTask {
	
	protected PuridiomProcess getProcess(String filename, String organizationId) throws Exception {
		PuridiomProcess process = null;
		try {
			PuridiomProcessLoader processLoader = new VendorRegistrationProcessLoader(organizationId);
			process = processLoader.loadProcess(filename);
		}
		catch (Exception e) {
			throw e;
		}
		
		return process;
	}
}

