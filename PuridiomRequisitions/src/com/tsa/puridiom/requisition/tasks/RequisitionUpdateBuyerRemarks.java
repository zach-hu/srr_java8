/*
 * Created on August 07, 2007
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author kathleen
 */
public class RequisitionUpdateBuyerRemarks extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("RequisitionHeader_buyerRemarks")) {

				Object buyerRemarksObj = incomingRequest.get("RequisitionHeader_buyerRemarks");
				List icReqHeaderList = (List) incomingRequest.get("icReqHeaderList");

				if (buyerRemarksObj instanceof String[]) {
					String[] buyerRemarksArray = (String[]) buyerRemarksObj;
					int	arraySize = buyerRemarksArray.length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						String buyerRemarks = buyerRemarksArray[i];
						if (!HiltonUtility.isEmpty(buyerRemarks))
						{
							PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
							PuridiomProcess process = processLoader.loadProcess("requisition-update-buyer-remarks.xml");

							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId", incomingRequest.get("organizationId"));
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));
							updateParameters.put("RequisitionHeader_icReqHeader", (String) icReqHeaderList.get(i));

							Iterator iterator = keySet.iterator();
							while (iterator.hasNext()) {
								String key = (String) iterator.next();
								if (key.indexOf("RequisitionHeader_") == 0) {
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
								throw new TsaException("Error ocurred updating buyer remarks!");
							}
						}
					}
				}
				else
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("requisition-update-buyer-remarks.xml");

					incomingRequest.put("RequisitionHeader_icReqHeader", (String) icReqHeaderList.get(0));

					process.executeProcess(incomingRequest);
				}
			}
			else
			{
				throw new Exception("The value for Buyer Remarks was not found.");
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
