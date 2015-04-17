/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class InvBinLocationAddKitList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("InvBinLocation_aisle")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));

				String addInvBinLocation = HiltonUtility.ckNull((String)incomingRequest.get("addInvBinLocation"));

				Object aisleObj = incomingRequest.get("InvBinLocation_aisle");
                List invBinLocationList = new ArrayList();

				if (aisleObj instanceof String[]) {
					int	arraySize = ((String[]) aisleObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						if (addInvBinLocation.equalsIgnoreCase("Y")) {
							updateParameters.put("addBinList", "false");
						} else {
							updateParameters.put("addBinList", "true");
						}

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("InvBinLocation_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}

						if (!addInvBinLocation.equalsIgnoreCase("Y")) {
							updateParameters.put("InvBinLocation_status", "00");
						}

						PuridiomProcess process = processLoader.loadProcess("invbinlocation-add-by-id.xml");
						process.executeProcess(updateParameters);

                        InvBinLocation invBinLocation = (InvBinLocation) updateParameters.get("invBinLocation");
                        if (invBinLocation != null) {
                            invBinLocationList.add(invBinLocation);
                        }
					}
				}
				else {
					if (!addInvBinLocation.equalsIgnoreCase("Y")) {
						incomingRequest.put("InvBinLocation_status", "02");
					}

				    PuridiomProcess process = processLoader.loadProcess("invbinlocation-add-by-id.xml");
					process.executeProcess(incomingRequest);

                    InvBinLocation invBinLocation = (InvBinLocation) incomingRequest.get("invBinLocation");
                    if (invBinLocation != null) {
                        invBinLocationList.add(invBinLocation);
                    }
                }
                result = invBinLocationList;
                incomingRequest.put("invBinLocationList", invBinLocationList);
			}
			else {
			    //No Bin Locations to add
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
