/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.invformpart.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class InvFormPartUpdateByLine extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try 
		{
			if (incomingRequest.containsKey("InvFormPart_itemNumber")) 
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("invformpart-update-row.xml");
				Object formParts = incomingRequest.get("InvFormPart_formPart");
				
				if (formParts instanceof String[]) 
				{
					int	arraySize = ((String[]) formParts).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) 
					{
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("InvFormPart_itemNumber",incomingRequest.get("InvFormPart_itemNumber")) ;
						
						
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) 
						{
							String key = (String) iterator.next();
							if (key.indexOf("InvFormPart_") == 0) 
							{
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) 
								{
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						process.executeProcess(updateParameters);
					}
				}
				else 
				{
						incomingRequest.put("InvFormPart_itemNumber",incomingRequest.get("InvFormPart_itemNumber")) ;
						incomingRequest.put("InvFormPart_formPart",incomingRequest.get("InvFormPart_formPart")) ;
						process.executeProcess(incomingRequest);
				}
			}
			else 
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("The value for InvFormPart_itemNumber was not found.");
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
