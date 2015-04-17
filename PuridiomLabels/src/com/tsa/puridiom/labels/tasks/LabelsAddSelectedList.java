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

public class LabelsAddSelectedList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("Labels_labelCode")) {

				Object labelCodeObj = incomingRequest.get("Labels_labelCode");
				Object selectedRowObj = incomingRequest.get("as_selectedRow");

				if (labelCodeObj instanceof String[]) {
					int	arraySize = ((String[]) labelCodeObj).length;
					Set keySet = incomingRequest.keySet();
					String[] rowSelectedArray = null;

					if (selectedRowObj instanceof String[]) {
						rowSelectedArray = (String[]) selectedRowObj;
					}


					for (int i=0; i < arraySize; i++) {
						String rowSelected = "";
						if (rowSelectedArray.length >= arraySize) {
							rowSelected = rowSelectedArray[i];
						}
						if (rowSelected.equals("Y")) {
							PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
							PuridiomProcess process = processLoader.loadProcess("labels-create.xml");

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
								throw new TsaException("Error occurred adding label [" + updateParameters.get("Labels_labelCode") + "]!");
							}
						}
					}
				}
				else
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("labels-create.xml");

					String rowSelected = "";

					if (selectedRowObj instanceof String) {
						rowSelected = (String) selectedRowObj;
					}
					if (rowSelected.equals("Y")) {
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
				throw new Exception("No entries for Labels_labelCode were not found.");
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
