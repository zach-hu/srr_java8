package com.tsa.puridiom.supplierportal.handlers;

import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RegisterUserPasswordHelpHandler implements IHandler {

	public Map  handleRequest (Map incomingRequest) throws Exception {
		try {
			String	organizationId = (String) incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
			//PuridiomProcess process = processLoader.loadProcess("user-password-help.xml");
			PuridiomProcess process = processLoader.loadProcess("registeruser-passwordhelp.xml");
			process.executeProcess(incomingRequest);

			String	errorMsg = (String) incomingRequest.get("errorMsg");
			if (errorMsg != null && errorMsg.trim().length() > 0) {
			    incomingRequest.put("viewPage", incomingRequest.get("resetFailurePage"));
			} else {
				if (process.getStatus() == Status.SUCCEEDED) {
					incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				} else {
					incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
				}
			}
		}
		catch (Exception exception) {
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
		}
		finally {
			if (incomingRequest.get("viewPage") == null) {
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}