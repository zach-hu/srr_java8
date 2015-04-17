package com.tsa.puridiom.doccomment.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DocCommentAddStandard extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("StdComment_commentId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("doccomment-add-standard-by-id.xml");

				Object obj = incomingRequest.get("StdComment_commentId-Input_checkbox");
				if (obj instanceof String[]) {
					String commentIds[] = (String[]) obj;
					int	arraySize = commentIds.length;
					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("DocComment_icHeader", incomingRequest.get("DocComment_icHeader"));
						updateParameters.put("DocComment_icLine", incomingRequest.get("DocComment_icLine"));
						updateParameters.put("Default_referenceType", incomingRequest.get("Default_referenceType"));
						updateParameters.put("Default_commentPlace", incomingRequest.get("Default_commentPlace"));
						updateParameters.put("StdComment_commentId", commentIds[i]);
						process.executeProcess(updateParameters);
					}
				}else {
						Object stdcommentid = incomingRequest.get("StdComment_commentId");
						if (stdcommentid instanceof String[]) {
							incomingRequest.put("StdComment_commentId", incomingRequest.get("StdComment_commentId-Input_checkbox"));
						}
						process.executeProcess(incomingRequest);
				}


			}
			else {
				throw new Exception("The value for StandardComment_commentId was not found.");
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