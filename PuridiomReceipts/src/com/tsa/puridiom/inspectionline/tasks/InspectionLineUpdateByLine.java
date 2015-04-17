package com.tsa.puridiom.inspectionline.tasks;

import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;

public class InspectionLineUpdateByLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("InspectionLine_icInspNo")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("inspection-update-line-row.xml");

				Object inspCritNoObj = incomingRequest.get("InspectionLine_critNo");
				String inspectCode = (String) incomingRequest.get("InspectionLine_inspectCode") ;
				if (! HiltonUtility.isEmpty(inspectCode)) {
					if (inspCritNoObj instanceof String[]) {
						int	arraySize = ((String[]) inspCritNoObj).length;
						Set keySet = incomingRequest.keySet();

						for (int i=0; i < arraySize; i++) {
							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId", incomingRequest.get("organizationId"));
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));
							updateParameters.put("InspectionLine_icInspNo",incomingRequest.get("InspectionLine_icInspNo")) ;
							updateParameters.put("InspectionLine_icInspLine",incomingRequest.get("InspectionLine_icInspLine")) ;

							Iterator iterator = keySet.iterator();
							while (iterator.hasNext()) {
								String key = (String) iterator.next();
								if (key.indexOf("InspectionLine_") == 0) {
									Object obj =  incomingRequest.get(key);
									if (obj instanceof String[]) {
										String arrayObj[] = (String[]) obj;
										updateParameters.put(key, arrayObj[i]);
									}
								}
							}
							updateParameters.put("InspectionLine_inspectCode", incomingRequest.get("InspectionLine_inspectCode")) ;
							process.executeProcess(updateParameters);
						}
					}
					else {
							incomingRequest.put("InspectionLine_icInspNo",incomingRequest.get("InspectionLine_icInspNo")) ;
							incomingRequest.put("InspectionLine_icInspLine",incomingRequest.get("InspectionLine_icInspLine")) ;
							incomingRequest.put("InspectionLine_seqNo", "1");
							process.executeProcess(incomingRequest);
					}
				}

			}
			else {
				throw new Exception("The value for InspectionLine_icInspNo was not found.");
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
