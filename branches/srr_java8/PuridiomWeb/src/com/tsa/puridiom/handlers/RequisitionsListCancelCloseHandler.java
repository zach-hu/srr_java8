package com.tsa.puridiom.handlers;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionsListCancelCloseHandler implements IHandler {

	public Map handleRequest(Map incomingRequest) throws Exception {
		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String) incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("requisitions-list-cancel-close.xml");

			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED) {
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			} else {
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		} catch (Exception exception) {
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		}

		return incomingRequest;
	}

}
