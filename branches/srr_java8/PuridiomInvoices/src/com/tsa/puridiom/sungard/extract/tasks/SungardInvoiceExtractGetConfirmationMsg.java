package com.tsa.puridiom.sungard.extract.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.io.File;
import java.util.Map;

public class SungardInvoiceExtractGetConfirmationMsg extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");

		File extractFile = (File) incomingRequest.get("extractFile");
		String	confirmationMsg = "";
		
		if (extractFile.exists()) {
		    confirmationMsg = "This invoice has been extracted to " + extractFile.getName();
			this.setStatus(Status.SUCCEEDED);
		}
		else {
		    confirmationMsg = "There was a problem extracting the invoice.";
		    this.setStatus(Status.FAILED);
		}
		
		return confirmationMsg;
	}

}