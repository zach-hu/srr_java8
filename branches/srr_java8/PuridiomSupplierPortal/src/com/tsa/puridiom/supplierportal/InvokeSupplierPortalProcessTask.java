/*
 * Created on May 27, 2004
 */
package com.tsa.puridiom.supplierportal;

import com.tsagate.foundation.processengine.*;

/**
 * @author Kelli
 */
public class InvokeSupplierPortalProcessTask extends InvokePuridiomProcessTask {
	
	protected PuridiomProcess getProcess(String filename, String organizationId) throws Exception {
		PuridiomProcess process = null;
		try {
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
			process = processLoader.loadProcess(filename);
		}
		catch (Exception e) {
			throw e;
		}
		
		return process;
	}
}

