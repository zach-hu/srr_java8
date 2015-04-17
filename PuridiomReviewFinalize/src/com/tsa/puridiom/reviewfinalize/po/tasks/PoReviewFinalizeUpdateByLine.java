/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.reviewfinalize.po.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Administrator
 */
public class PoReviewFinalizeUpdateByLine extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
				Object commentIdObj = incomingRequest.get("ReviewFinalize_step");

				if (commentIdObj instanceof String[])
				{
					int	arraySize = ((String[]) commentIdObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++)
					{
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("po-reviewfinalize-update-line-row.xml");

						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("ReviewFinalize_icHeader", incomingRequest.get("ReviewFinalize_icHeader"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext())
						{
							String key = (String) iterator.next();
							if (key.indexOf("ReviewFinalize_") == 0)
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
					PuridiomProcess process = processLoader.loadProcess("po-reviewfinalize-update-line-row.xml");
					process.executeProcess(incomingRequest);
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
