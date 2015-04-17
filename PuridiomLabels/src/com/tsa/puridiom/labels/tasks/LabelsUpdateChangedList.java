package com.tsa.puridiom.labels.tasks;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class LabelsUpdateChangedList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("Labels_icLabel")) {

				Object icLabelObj = incomingRequest.get("Labels_icLabel");
				Object rowStatusObj = incomingRequest.get("as_rowstatus");

				if (icLabelObj instanceof String[]) {
					int	arraySize = ((String[]) icLabelObj).length;
					Set keySet = incomingRequest.keySet();
					String[] rowStatusArray = null;

					if (rowStatusObj instanceof String[]) {
						rowStatusArray = (String[]) rowStatusObj;
					}


					for (int i=0; i < arraySize; i++) {
						String rowStatus = "";
						if (rowStatusArray.length == arraySize) {
							rowStatus = rowStatusArray[i];
						}
						if (rowStatus.equals("U")) {
							PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
							PuridiomProcess process = processLoader.loadProcess("labels-update-by-id.xml");

							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId", incomingRequest.get("organizationId"));
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));

							Iterator iterator = keySet.iterator();
							while (iterator.hasNext()) {
								String key = (String) iterator.next();
								if (key.indexOf("Labels_") == 0) {
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
								throw new TsaException("Error ocurred updating labels!");
							}
						}
					}
				}
				else
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("labels-update-by-id.xml");

					String rowStatus = "";

					if (rowStatusObj instanceof String) {
						rowStatus = (String) rowStatusObj;
					}
					if (rowStatus.equals("U")) {
						process.executeProcess(incomingRequest);

						if(process.getStatus() != Status.SUCCEEDED)
						{
							this.setStatus(Status.FAILED);
						}
					}
				}
			}
			else
			{
				throw new Exception("The value for Labels_icLabel was not found.");
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
