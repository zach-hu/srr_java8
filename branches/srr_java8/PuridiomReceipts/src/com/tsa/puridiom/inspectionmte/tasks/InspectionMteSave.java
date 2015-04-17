package com.tsa.puridiom.inspectionmte.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InspectionMte;
import com.tsa.puridiom.entity.InspectionMtePK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

public class InspectionMteSave extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String icRecHeader = (String) incomingRequest.get("InspectionMte_icRecHeader") ;
			String icRecLine = (String) incomingRequest.get("InspectionMte_icRecLine") ;
			String icMsrLine = (String) incomingRequest.get("InspectionMte_icMsrLine") ;
			String icInspNo = (String) incomingRequest.get("InspectionMte_icInspNo") ;
			String userId = (String) incomingRequest.get("userId") ;
			String organizationId = (String) incomingRequest.get("organizationId") ;
	        String userDateFormat = (String) incomingRequest.get("userDateFormat");

	        if (HiltonUtility.isEmpty(userDateFormat)) {
	            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
	        }

			int count = 0;

			if (incomingRequest.containsKey("InspectionMte_mteNo")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("inspection-mte-add.xml");

				Object mteNoObj = incomingRequest.get("InspectionMte_mteNo");
				Object descriptionObj = incomingRequest.get("InspectionMte_description");
				Object dateUsedObj = incomingRequest.get("InspectionMte_dateUsed");
				Object calDateObj = incomingRequest.get("InspectionMte_calDate");
				if (mteNoObj instanceof String[]) {
					int	arraySize = ((String[]) mteNoObj).length;

					for (int i=0; i < arraySize; i++) {
						String mteNo = ((String[]) mteNoObj)[i] ;
						String description = ((String[]) descriptionObj)[i];
						String dateUsed = ((String[]) dateUsedObj)[i] ;
						String calDate = ((String[]) calDateObj) [i] ;

						if ( ! HiltonUtility.isEmpty(mteNo) || ! HiltonUtility.isEmpty(description)) {
							count++ ;
							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId",organizationId);
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));
							updateParameters.put("InspectionMte_icInspNo",icInspNo) ;
							updateParameters.put("InspectionMte_icMsrLine",icMsrLine) ;
							updateParameters.put("InspectionMte_icRecHeader",icRecHeader) ;
							updateParameters.put("InspectionMte_icRecLine",icRecLine) ;
							updateParameters.put("InspectionMte_lastChange", Dates.today(userDateFormat));
							updateParameters.put("InspectionMte_lastChangedBy", userId);
							updateParameters.put("InspectionMte_description", description) ;
							updateParameters.put("InspectionMte_dateUsed",dateUsed) ;
							updateParameters.put("InspectionMte_calDate", calDate) ;
							updateParameters.put("InspectionMte_mteNo", mteNo) ;
							updateParameters.put("InspectionMte_keySequence", Integer.toString(count)) ;

							process.executeProcess(updateParameters);
						}
					}
				} else {
					String mteNo = (String) mteNoObj ;
					String description = (String) descriptionObj;
					String dateUsed = (String) dateUsedObj ;
					String calDate = (String) calDateObj;

					if ( ! HiltonUtility.isEmpty(mteNo) || ! HiltonUtility.isEmpty(description)) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId",organizationId);
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("InspectionMte_icInspNo",icInspNo) ;
						updateParameters.put("InspectionMte_icMsrLine",icMsrLine) ;
						updateParameters.put("InspectionMte_icRecHeader",icRecHeader) ;
						updateParameters.put("InspectionMte_icRecLine",icRecLine) ;
						updateParameters.put("InspectionMte_keySequence", "1") ;
						updateParameters.put("InspectionMte_lastChange", Dates.today(userDateFormat));
						updateParameters.put("InspectionMte_lastChangedBy", userId);

						updateParameters.put("InspectionMte_description", description) ;
						updateParameters.put("InspectionMte_dateUsed",dateUsed) ;
						updateParameters.put("InspectionMte_calDate", calDate) ;
						updateParameters.put("InspectionMte_mteNo", mteNo) ;

						process.executeProcess(updateParameters);
					}
				}
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
