package com.tsa.puridiom.stdattachment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StdAttachmentUpdateAll extends Task {
    
	public Object executeTask(Object object) throws Exception {
		Object ret = null;
		
		try {
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		
			Object docIcObj = incomingRequest.get("StdAttachment_docIc");
			
			if (docIcObj instanceof String[]) {
				int	arraySize = ((String[]) docIcObj).length;
				Set keySet = incomingRequest.keySet();
				
				for(int i = 0; i < arraySize; i++) {
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("stdattachment-update-by-id.xml");
				    
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					
					Iterator iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						if (key.indexOf("StdAttachment_") == 0) {
							Object obj =  incomingRequest.get(key);
							if (obj instanceof String[]) {
								String arrayObj[] = (String[]) obj;
								updateParameters.put(key, arrayObj[i]);
							}
						}
					}
					process.executeProcess(updateParameters);
					
					if (process.getStatus() != Status.SUCCEEDED) {
						throw new TsaException("Error occurred updating standard attachments!");
					}
				}
			}
			else {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("stdattachment-update-by-id.xml");
				
				process.executeProcess(incomingRequest);
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
