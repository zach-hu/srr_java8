package com.tsa.puridiom.inspectionmfr.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;

public class InspectionMfrUpdateFromList extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String icInspNoString = (String) incomingRequest.get("InspectionMfr_icInspNo");
			if (icInspNoString == null) icInspNoString = (String) incomingRequest.get("InspectionHeader_icInspNo");

			String icMsrLineString = (String) incomingRequest.get("InspectionMfr_icMsrLine");
			if (icMsrLineString == null) icInspNoString = (String) incomingRequest.get("InspectionHeader_icMsrLine");

			if (! HiltonUtility.isEmpty(icInspNoString)) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("inspectionmfr-update-from-list.xml");

				Object mfrNameObj =  incomingRequest.get("InspectionMfr_mfrName") ;
				if (mfrNameObj instanceof String[]) {
					int	arraySize = ((String[]) mfrNameObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("InspectionMfr_icInspNo",icInspNoString) ;
						updateParameters.put("InspectionMfr_icMsrLine",icMsrLineString) ;
						updateParameters.put("InspectionMfr_icMfrNo","") ;
						boolean hasData = false ;
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("InspectionMfr_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									if (key.indexOf("documentType") > 0  ||
											key.indexOf("mfrName") > 0  ||
											key.indexOf("mfrNo") > 0  ||
											key.indexOf("documentType") > 0 ) {
										if (! HiltonUtility.isEmpty((String) arrayObj[i])) {
											hasData = true ;
										}
									}
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						if (hasData) {
							process.executeProcess(updateParameters);
						}
					}
				}
				else {
					String mfrNo =  (String) incomingRequest.get("InspectionMfr_mfrNo") ;
					String mfrName =  (String) incomingRequest.get("InspectionMfr_mfrName") ;
					String mfrHeatLot =  (String) incomingRequest.get("InspectionMfr_mfrHeatLog") ;
					String mfrDocumentType =  (String) incomingRequest.get("InspectionMfr_documentType") ;
					incomingRequest.put("InspectionMfr_icInspNo",icInspNoString) ;
					incomingRequest.put("InspectionMfr_icMsrLine",icMsrLineString) ;
					incomingRequest.put("InspectionMfr_icMfrNo","") ;
					if (! (HiltonUtility.isEmpty(mfrNo)
							&& HiltonUtility.isEmpty(mfrName)
							&& HiltonUtility.isEmpty(mfrHeatLot)
							&& HiltonUtility.isEmpty(mfrDocumentType))) {
						process.executeProcess(incomingRequest);
					}
				}
			}
			else {
				throw new Exception("The value for InspectionMfr_icInspNo was not found.");
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
