package com.tsa.puridiom.inspectionstd.tasks;
import java.util.*;

import com.tsagate.foundation.processengine.*;

public class InspectionStdUpdateByStdCriteria extends Task
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
			if (incomingRequest.containsKey("InspectionStd_standardCode"))
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("inspectionstd-update-std-row.xml");
				Object inspObj = incomingRequest.get("InspectionStd_inspectCode");

				if (inspObj instanceof String[])
				{
					int arraySize = ((String[]) inspObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i = 0; i < arraySize; i++)
					{
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("InspectionStd_icInspStd",  incomingRequest.get("InspectionStd_icInspStd")) ;
						updateParameters.put("InspectionStd_standardCode", incomingRequest.get("InspectionStd_standardCode"));
						updateParameters.put("InspectionStd_description", incomingRequest.get("InspectionStd_description"));
						updateParameters.put("InspectionStd_cgdNo", incomingRequest.get("InspectionStd_cgdNo"));
						updateParameters.put("InspectionStd_cgdRev", incomingRequest.get("InspectionStd_cgdRev"));
						updateParameters.put("InspectionStd_inspectType", incomingRequest.get("InspectionStd_inspectType"));
						updateParameters.put("InspectionStd_defaultFlag", incomingRequest.get("InspectionStd_defaultFlag_" + i));
						updateParameters.put("InspectionStd_owner", incomingRequest.get("InspectionStd_owner"));
						updateParameters.put("InspectionStd_status", incomingRequest.get("InspectionStd_status"));
						updateParameters.put("InspectionStd_dateEntered", incomingRequest.get("InspectionStd_dateEntered"));
						updateParameters.put("InspectionStd_dateExpires", incomingRequest.get("InspectionStd_dateExpires"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("InspectionStd_") == 0) {
								Object obj =  incomingRequest.get(key);
								System.out.println(key + "=" + obj) ;
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
									System.out.println(key + "=" + arrayObj[i]) ;
								}
							}
						}
						process.executeProcess(updateParameters);
					}
				}
				else
				{
					incomingRequest.put("InspectionStd_defaultFlag", incomingRequest.get("InspectionStd_defaultFlag_0"));
					process.executeProcess(incomingRequest);
				}
			}
			else
			{
				throw new Exception("Unable to find Standard Code");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
