/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.doccomment.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Administrator 
 */
public class DocCommentUpdateByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("DocComment_icHeader")) {
				
				Object commentIdObj = incomingRequest.get("DocComment_commentId");
				
				if (commentIdObj instanceof String[]) {
					int	arraySize = ((String[]) commentIdObj).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) {
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("doccomment-update-line-row.xml");
						
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("DocComment_commentOrder",Integer.toString(i + 1)) ;
						updateParameters.put("DocComment_icHeader",incomingRequest.get("DocComment_icHeader")) ;
						updateParameters.put("DocComment_icLine",incomingRequest.get("DocComment_icLine")) ;
						updateParameters.put("DocComment_referenceType",incomingRequest.get("Default_referenceType")) ;
						updateParameters.put("DocText_referenceType",incomingRequest.get("Default_referenceType")) ;
						
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("DocComment_") == 0 || key.indexOf("DocText_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						process.executeProcess(updateParameters);
						if(process.getStatus() != Status.SUCCEEDED)
						{
							this.setStatus(Status.FAILED);
							throw new TsaException("Error ocurred updating comments!");
						}
					}
				}
				else 
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("doccomment-update-line-row.xml");
					
					incomingRequest.put("DocComment_referenceType",incomingRequest.get("Default_referenceType")) ;
					incomingRequest.put("DocText_referenceType",incomingRequest.get("Default_referenceType")) ;
											
					process.executeProcess(incomingRequest);
				}
			}
			else 
			{
				throw new Exception("The value for DocComment_icHeader was not found.");
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
