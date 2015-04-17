package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DocAttachmentAddStandard extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("StdAttachment_docIc")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("docattachment-add-standard-by-id.xml");
				Object docIcObj = incomingRequest.get("StdAttachment_docIc-Input_checkbox");

				if (docIcObj == null)
				{
					docIcObj = incomingRequest.get("StdAttachment_docIc");
				}
//				Object inputChkBoxObj = incomingRequest.get("Input_checkbox");

				if (docIcObj instanceof String[]) {
					//if the docIcObj is an array, loop through list of docIc values and add each as a DocAttachment
					String docIcs[] = (String[]) docIcObj;
//					String inputChkBoxs[] = (String[]) inputChkBoxObj;

					int	arraySize = docIcs.length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
//						if (!HiltonUtility.isEmpty(docIcs[i].trim()) && !HiltonUtility.isEmpty(inputChkBoxs[i]) && HiltonUtility.ckNull(inputChkBoxs[i]).equalsIgnoreCase("Y"))
//						{
							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId", incomingRequest.get("organizationId"));
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));
							updateParameters.put("DocAttachment_icHeader", incomingRequest.get("DocAttachment_icHeader"));
							updateParameters.put("DocAttachment_icLine", incomingRequest.get("DocAttachment_icLine"));
							updateParameters.put("DocAttachment_docSource", incomingRequest.get("Default_referenceType"));
							updateParameters.put("StdAttachment_docIc", docIcs[i]);

							process.executeProcess(updateParameters);
//						}
					}
				}
				else {
					if (docIcObj instanceof String) {
						incomingRequest.put("StdAttachment_docIc", (String)docIcObj);
						process.executeProcess(incomingRequest);
					}
				}
			}
			else {
				throw new Exception("The value for StdAttachment_docIc was not found.");
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