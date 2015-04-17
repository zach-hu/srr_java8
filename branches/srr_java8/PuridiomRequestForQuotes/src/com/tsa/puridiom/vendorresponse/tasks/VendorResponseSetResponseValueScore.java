/*
 * Created on Oct 25, 2006
 */
package com.tsa.puridiom.vendorresponse.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.StdQuestionRatingMethod;
import com.tsa.puridiom.entity.ResponseValue;
import com.tsa.puridiom.entity.RfqQuestion;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorResponseSetResponseValueScore extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
		    RfqQuestion rfqQuestion = (RfqQuestion) incomingRequest.get("rfqQuestion");
		    String response = (String) incomingRequest.get("VendorResponse_response");
			
			if (rfqQuestion != null)
			{
			    String ratingMethod = rfqQuestion.getRatingMethod();
			    
			    if (!ratingMethod.equals(StdQuestionRatingMethod.MANUAL)) {
			        Map responseValueMap = rfqQuestion.getResponseValueMap();
				    if (responseValueMap != null)
				    {
				        ResponseValue responseValue = (ResponseValue) responseValueMap.get(response);
				        if (responseValue != null)
				        {
					        if (!incomingRequest.containsKey("VendorResponse_rating"))
					        {
					            incomingRequest.put("VendorResponse_score", String.valueOf(responseValue.getRating()));
					            incomingRequest.put("VendorResponse_status", DocumentStatus.EVALUATION_COMPLETE);
					        }
				        }
				    }
			    }
			}   
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
