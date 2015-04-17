package com.tsa.puridiom.docattachment.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsa.puridiom.property.PropertiesManager;

public class DocAttachmentAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		    incomingRequest.put("DocAttachment_docIc",ukg.getUniqueKey().toString());
		    String	organizationId = (String) incomingRequest.get("organizationId") ;
		    String	userId = (String) incomingRequest.get("userId") ;
		    PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
		    String userTimeZone = (String) incomingRequest.get("userTimeZone");
		    String today = Dates.today(propertiesManager.getProperty("MISC","DATEFORMAT","MM-dd-yyyy"), userTimeZone);
		    
		    if (!incomingRequest.containsKey("DocAttachment_icHeader")) {
		        incomingRequest.put("DocAttachment_icHeader", "0");
		    }
		    if (!incomingRequest.containsKey("DocAttachment_icLine")) {
		    	incomingRequest.put("DocAttachment_icLine", "0");
		    }
			if (!incomingRequest.containsKey("DocAttachment_icReqHeader")) {
			    incomingRequest.put("DocAttachment_icReqHeader", "0");
			}
			if (!incomingRequest.containsKey("DocAttachment_docSource")) {
			    incomingRequest.put("DocAttachment_docSource", "");
			}
			if (!incomingRequest.containsKey("DocAttachment_docTitle")) {
			    incomingRequest.put("DocAttachment_docTitle", "");
			}
			if (!incomingRequest.containsKey("DocAttachment_docFilename")) {
			    incomingRequest.put("DocAttachment_docFilename", "");
			}
			if (!incomingRequest.containsKey("DocAttachment_docPrint")) {
			    incomingRequest.put("DocAttachment_docPrint", "N");
			}
			if (!incomingRequest.containsKey("DocAttachment_docPost")) {
			    incomingRequest.put("DocAttachment_docPost", "N");
			}
			if (!incomingRequest.containsKey("DocAttachment_uploadedBy")){
				incomingRequest.put("DocAttachment_uploadedBy", userId);
			}
			if (!incomingRequest.containsKey("DocAttachment_dateUploaded")){
				incomingRequest.put("DocAttachment_dateUploaded", today);
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}