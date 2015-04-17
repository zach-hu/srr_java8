/*
 * Created on April 12, 2006
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class RequisitionLineUpdateByLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("RequisitionLine_description"))
			{
				Object lineitems = incomingRequest.get("RequisitionLine_description");

				if (lineitems instanceof String[])
				{
					String[] descriptions = (String[]) lineitems;

					Set keySet = incomingRequest.keySet();

					for (int i = 0; i < descriptions.length; i++)
					{
						String itemDesc = descriptions[i];
						if (!HiltonUtility.isEmpty(itemDesc) || i == 0)
						{
							PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
							PuridiomProcess process = processLoader.loadProcess("requisitionline-update-line-row.xml");

							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId", incomingRequest.get("organizationId"));
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));
							updateParameters.put("RequisitionHeader_icReqHeader", incomingRequest.get("RequisitionHeader_icReqHeader"));
							updateParameters.put("RequisitionLine_icReqHeader", incomingRequest.get("RequisitionLine_icReqHeader"));
							updateParameters.put("RequisitionLine_lineNumber", String.valueOf(i + 1));
							updateParameters.put("createAction", "SAVE");

							Iterator iterator = keySet.iterator();
							while (iterator.hasNext())
							{
								String key = (String) iterator.next();
								if (key.indexOf("RequisitionLine_") == 0)
								{
									Object obj =  incomingRequest.get(key);
									if (obj instanceof String[])
									{
										String arrayObj[] = (String[]) obj;
										updateParameters.put(key, arrayObj[i]);
									}
									else if (key.indexOf("udf") > 0)
									{
										updateParameters.put(key, obj);
									}
								}
							}
							process.executeProcess(updateParameters);
						}
					}
				}
				else
				{
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("requisitionline-update-line-row.xml");
						process.executeProcess(incomingRequest);
				}
			}
			else
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("The value for RequisitionLine_description was not found.");
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
