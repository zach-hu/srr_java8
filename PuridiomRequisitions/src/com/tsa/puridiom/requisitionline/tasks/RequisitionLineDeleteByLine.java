/*
 * Created on April 19, 2006
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class RequisitionLineDeleteByLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("deleteLine_lineIc"))
			{
				Object lineitems = incomingRequest.get("deleteLine_lineIc");

				if (lineitems instanceof String[])
				{
					int	arraySize = ((String[]) lineitems).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++)
					{
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("requisitionline-delete-by-lineic.xml");

						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("RequisitionLine_icReqHeader",incomingRequest.get("RequisitionLine_icReqHeader"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext())
						{
							String key = (String) iterator.next();
							if (key.indexOf("deleteLine_") == 0)
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
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("requisitionline-delete-by-lineic.xml");
						process.executeProcess(incomingRequest);
				}
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
